package org.mpatapenka.ssp.transform.domain;

import com.sun.jdi.PrimitiveValue;
import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Image;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.mpatapenka.ssp.service.ImageService;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageTransformer extends NullSafeTransformer<ImageEntity, Image> {
    private final ImageService imageService;

    @Override
    Image safeForward(@Nonnull ImageEntity imageEntity) {
        Image image = new Image();
        image.setId(imageEntity.getId());
        image.setOriginalName(imageEntity.getOriginalName());
        image.setPath(imageEntity.getPath());
        return image;
    }

    @Override
    ImageEntity safeBackward(@Nonnull Image image) {
        List<ImageEntity> imageEntities = imageService.uploadImages(Collections.singletonList(image.getContent()));
        return imageEntities.get(0);
    }
}