package org.mpatapenka.ssp.service.impl;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.mpatapenka.ssp.SspProperties;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.mpatapenka.ssp.exception.ImageNotFoundException;
import org.mpatapenka.ssp.exception.ServiceException;
import org.mpatapenka.ssp.repository.IdRepository;
import org.mpatapenka.ssp.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class FileSystemImageService implements ImageService {
    private final Path storageLocation;
    private final IdRepository<ImageEntity> imageRepository;

    public FileSystemImageService(SspProperties props, IdRepository<ImageEntity> imageRepository) {
        this.storageLocation = Paths.get(props.getImageStorageLocation()).toAbsolutePath().normalize();
        this.imageRepository = imageRepository;

        log.debug("Storage location is: {}.", storageLocation);

        try {
            Files.createDirectories(storageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the uploaded images will be stored!", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource loadAsResource(long id) {
        Exception exceptionToWrap = null;
        Optional<ImageEntity> imageEntityOptional = imageRepository.findById(id);
        if (imageEntityOptional.isPresent()) {
            Path imagePath = storageLocation.resolve(imageEntityOptional.get().getPath()).normalize();
            log.debug("Loading image by path: {}", imagePath);
            try {
                Resource resource = new UrlResource(imagePath.toUri());
                if (resource.exists() && resource.isReadable()) {
                    return resource;
                }
            } catch (MalformedURLException e) {
                exceptionToWrap = e;
            }
        }
        throw new ImageNotFoundException("File not found or doesn't have read permissions, id: " + id, exceptionToWrap);
    }

    @Override
    public ImageEntity store(MultipartFile image) {
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        log.debug("File name to save: {}", filename);
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
            imageEntity.setOriginalName(filename);
            imageEntity.setPath(systemPath);
            return imageRepository.save(imageEntity);
        } catch (IOException e) {
            throw new ServiceException("Could not store the image " + filename, e);
        }
    }

    @Override
    @Transactional
    public Collection<ImageEntity> store(MultipartFile[] images) {
        return Arrays.stream(images)
                .filter(Objects::nonNull)
                .map(this::store)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean remove(Iterable<ImageEntity> imageEntities) {
        AtomicInteger failedImages = new AtomicInteger(0);
        StreamSupport.stream(imageEntities.spliterator(), true)
                .filter(Objects::nonNull)
                .forEach(img -> {
                    try {
                        Files.deleteIfExists(storageLocation.resolve(img.getPath()));
                        log.debug("Removed from filesystem: {}, with path: {}.", img.getOriginalName(), img.getPath());
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