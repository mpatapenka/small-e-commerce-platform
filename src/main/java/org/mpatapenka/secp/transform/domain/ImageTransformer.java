package org.mpatapenka.secp.transform.domain;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.secp.domain.Image;
import org.mpatapenka.secp.entity.ImageEntity;
import org.mpatapenka.secp.repository.ImageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ImageTransformer extends NullSafeTransformer<ImageEntity, Image> {
    private final ImageRepository imageRepository;

    @Override
    Image safeForward(ImageEntity imageEntity) {
        Image image = new Image();
        image.setId(imageEntity.getId());
        image.setName(imageEntity.getOriginalName());
        image.setUrl(buildDownloadUri(imageEntity));
        return image;
    }

    @Override
    ImageEntity safeBackward(Image image) {
        return imageRepository.findById(image.getId()).orElse(null);
    }

    private String buildDownloadUri(ImageEntity imageEntity) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(imageEntity.getPath())
                .toUriString();
    }
}