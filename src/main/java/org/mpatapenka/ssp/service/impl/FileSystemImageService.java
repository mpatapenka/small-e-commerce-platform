package org.mpatapenka.ssp.service.impl;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.mpatapenka.ssp.SspProperties;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.mpatapenka.ssp.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class FileSystemImageService implements ImageService {
    private final Path rootLocation;

    public FileSystemImageService(SspProperties properties) {
        rootLocation = Paths.get(properties.getImageStorageLocation());
        log.debug("Root location Path: {}", rootLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize file storage!", e);
        }
    }

    @Override
    public Resource loadAsResource(String path) {
        try {
            Path file = rootLocation.resolve(path);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new RuntimeException("Could not read file: " + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + path, e);
        }
    }

    @Override
    public List<ImageEntity> uploadImages(List<MultipartFile> imageFiles) {
        return imageFiles.stream().map(this::upload).collect(Collectors.toList());
    }

    @Override
    public void removeImages(Iterable<ImageEntity> imageEntities) {
        StreamSupport.stream(imageEntities.spliterator(), true)
                .filter(Objects::nonNull)
                .forEach(i -> {
                    try {
                        Files.deleteIfExists(rootLocation.resolve(i.getPath()));
                    } catch (Exception e) {
                        log.error("{} can't be removed due to '{}'.", i.getPath(), e.getMessage());
                    }
                });
        try {
            Files.walk(rootLocation)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .filter(File::isDirectory)
                    .filter(f -> Objects.requireNonNull(f.listFiles()).length == 0)
                    .forEach(File::delete);
        } catch (IOException e) {
            log.warn("Empty directories cleanup has failed.", e);
        }
    }

    private ImageEntity upload(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return null;
        }

        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        log.debug("Original filename to upload: {}", filename);
        try {
            if (filename.contains("..")) {
                // This is a security check
                throw new SecurityException("Cannot store image with relative path outside current directory " + filename);
            }

            String systemPath = generateFilePath(filename);
            Path pathToSave = rootLocation.resolve(systemPath);
            log.debug("Generated system path: {}, resolved path to save: {}.", systemPath, pathToSave);
            Files.createDirectories(pathToSave);
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, pathToSave, StandardCopyOption.REPLACE_EXISTING);
            }

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setOriginalName(filename);
            imageEntity.setPath(systemPath);
            return imageEntity;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    private static String generateFilePath(String originalName) {
        String filenameHash = Hashing.sha256()
                .hashString(originalName + System.nanoTime(), StandardCharsets.UTF_8)
                .toString();

        StringBuilder generatedFilePath = new StringBuilder();
        for (int i = 0; i < 2 /* Only two dirs level allowed */; i++) {
            if (filenameHash.length() > 4) {
                generatedFilePath.append(filenameHash, 0, 2).append("/");
                filenameHash = filenameHash.substring(2);
            }
        }

        String extension = originalName.substring(originalName.lastIndexOf('.'));

        return generatedFilePath
                .append(filenameHash)
                .append(extension).toString();
    }
}