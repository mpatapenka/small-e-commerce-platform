package by.underwear.shop.service.impl;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import by.underwear.shop.UnderwearShopProperties;
import by.underwear.shop.domain.Image;
import by.underwear.shop.entity.ImageEntity;
import by.underwear.shop.exception.ImageNotFoundException;
import by.underwear.shop.exception.ServiceException;
import by.underwear.shop.repository.ImageRepository;
import by.underwear.shop.service.ImageService;
import by.underwear.shop.transform.Transformer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileSystemImageService implements ImageService {
    private final Path storageLocation;
    private final ImageRepository imageRepository;
    private final Transformer<ImageEntity, Image> imageTransformer;

    public FileSystemImageService(UnderwearShopProperties props, ImageRepository imageRepository,
            Transformer<ImageEntity, Image> imageTransformer) {
        this.storageLocation = Paths.get(props.getImageStorageLocation()).toAbsolutePath().normalize();
        this.imageRepository = imageRepository;
        this.imageTransformer = imageTransformer;

        log.debug("Storage location is: {}.", storageLocation);

        try {
            Files.createDirectories(storageLocation);
        } catch (IOException e) {
            throw new ServiceException("Could not create the directory where the uploaded images will be stored!", e);
        }
    }

    @Override
    public Image get(long id) {
        Exception exceptionToWrap = null;
        Optional<ImageEntity> imageEntityOptional = imageRepository.findById(id);
        if (imageEntityOptional.isPresent()) {
            Path imagePath = storageLocation.resolve(imageEntityOptional.get().getPath()).normalize();
            log.debug("Loading image by path: {}", imagePath);
            try {
                Resource resource = new UrlResource(imagePath.toUri());
                if (resource.exists() && resource.isReadable()) {
                    Image image = imageTransformer.transform(imageEntityOptional.get());
                    image.setContent(resource);
                    return image;
                }
            } catch (MalformedURLException e) {
                exceptionToWrap = e;
            }
        }
        throw new ImageNotFoundException("File not found or doesn't have read permissions, id: " + id, exceptionToWrap);
    }

    @Override
    public Image save(MultipartFile image) {
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        log.debug("File name to saveAll: {}", filename);
        try {
            // Check if the file's name contains invalid characters
            if (filename.contains("..")) {
                throw new ServiceException("Sorry! Filename contains invalid path sequence " + filename);
            }

            String systemPath = generateFilePath(filename);
            Path targetLocation = storageLocation.resolve(systemPath);

            log.debug("Generated system path: {}, resolved path to save: {}.", systemPath, targetLocation);

            Files.createDirectories(targetLocation);
            Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            ImageEntity imageEntity = new ImageEntity();
//            imageEntity.setOriginalName(filename);
            imageEntity.setPath(systemPath);
            return imageTransformer.transform(imageRepository.save(imageEntity));
        } catch (IOException e) {
            throw new ServiceException("Could not saveAll the image " + filename, e);
        }
    }

    @Override
    public Collection<Image> saveAll(MultipartFile[] images) {
        return Arrays.stream(images)
                .filter(Objects::nonNull)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeAll(Collection<Image> images) {
        AtomicInteger failedImages = new AtomicInteger(0);
        images.parallelStream()
                .filter(Objects::nonNull)
                .map(img -> imageRepository.findById(img.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(img -> {
                    try {
                        Files.deleteIfExists(storageLocation.resolve(img.getPath()));
//                        log.debug("Removed from filesystem: {}, with path: {}.", img.getOriginalName(), img.getPath());
                        imageRepository.delete(img);
                    } catch (Exception e) {
                        log.error("{} can't be removed due to '{}'.", img.getPath(), e.getMessage());
                        failedImages.incrementAndGet();
                    }
                });
        try {
            Files.walk(storageLocation)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .filter(File::isDirectory)
                    .filter(f -> Objects.requireNonNull(f.listFiles()).length == 0)
                    .forEach(File::delete);
        } catch (IOException e) {
            log.warn("Empty directories cleanup has failed.", e);
        }
        return failedImages.get() == 0;
    }

    private static String generateFilePath(String originalName) {
        String filenameHash = Hashing.sha256()
                .hashString(originalName + System.nanoTime(), StandardCharsets.UTF_8)
                .toString();

        StringBuilder generatedFilePath = new StringBuilder();
        for (int i = 0; i < 2 /* Only two dirs level allowed */; i++) {
            if (filenameHash.length() > 4) {
                generatedFilePath.append(filenameHash, 0, 2).append(File.separator);
                filenameHash = filenameHash.substring(2);
            }
        }

        int extensionPos = originalName.lastIndexOf('.');
        String extension = extensionPos > 0 ? originalName.substring(extensionPos) : "";

        return generatedFilePath
                .append(filenameHash)
                .append(extension).toString();
    }
}