package by.inlove.shop.transform.domain;

import by.inlove.shop.domain.Image;
import by.inlove.shop.entity.ImageEntity;
import by.inlove.shop.transform.Transformer;
import org.springframework.stereotype.Component;

@Component
public class Image2ImageEntityTransformer implements Transformer<Image, ImageEntity> {
    @Override
    public ImageEntity transform(Image source) {
        ImageEntity target = new ImageEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setUrl(source.getUrl());
        return target;
    }
}