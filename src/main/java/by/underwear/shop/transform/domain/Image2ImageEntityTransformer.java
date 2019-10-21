package by.underwear.shop.transform.domain;

import by.underwear.shop.domain.Image;
import by.underwear.shop.entity.ImageEntity;
import by.underwear.shop.transform.Transformer;
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