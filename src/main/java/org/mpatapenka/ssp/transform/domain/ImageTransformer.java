package org.mpatapenka.ssp.transform.domain;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Image;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.mpatapenka.ssp.repository.ImageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nonnull;

@Component
@RequiredArgsConstructor
public class ImageTransformer extends NullSafeTransformer<ImageEntity, Image> {
    private final ImageRepository imageRepository;

    @Override
    Image safeForward(@Nonnull ImageEntity imageEntity) {
        Image image = new Image();
        image.setId(imageEntity.getId());
        image.setName(imageEntity.getOriginalName());
        image.setDownloadUrl(buildDownloadUri(imageEntity));
        return image;
    }

    @Override
    ImageEntity safeBackward(@Nonnull Image image) {
        return imageRepository.findById(image.getId()).orElse(null);
    }

    private String buildDownloadUri(ImageEntity imageEntity) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(imageEntity.getPath())
                .toUriString();
    }
}