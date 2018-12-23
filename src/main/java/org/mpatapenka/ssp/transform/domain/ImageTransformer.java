package org.mpatapenka.ssp.transform.domain;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Image;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nonnull;

@Component
@RequiredArgsConstructor
public class ImageTransformer extends NullSafeTransformer<ImageEntity, Image> {
    @Override
    Image safeForward(@Nonnull ImageEntity imageEntity) {
        Image image = new Image();
        image.setId(imageEntity.getId());
        image.setName(imageEntity.getOriginalName());
        image.setDownloadUri(buildDownloadUri(imageEntity));
        return image;
    }

    @Override
    ImageEntity safeBackward(@Nonnull Image image) {
        throw new UnsupportedOperationException("Convert image to image entity is not supported!");
    }

    private String buildDownloadUri(ImageEntity imageEntity) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(imageEntity.getPath())
                .toUriString();
    }
}