package by.inlove.shop.transform.entity;

import by.inlove.shop.domain.Image;
import by.inlove.shop.entity.ImageEntity;
import by.inlove.shop.transform.Transformer;
import by.inlove.shop.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ImageEntity2ImageTransformer implements Transformer<ImageEntity, Image> {
    @Override
    public Image transform(ImageEntity source) {
        Image image = new Image();
        image.setId(source.getId());
        image.setName(source.getName());
        image.setUrl(Strings.isNotEmpty(source.getUrl())
                ? source.getUrl()
                : buildLocalDownloadUri(source.getPath()));
        return image;
    }

    private String buildLocalDownloadUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path)
                .toUriString();
    }
}