package by.inlove.shop.transform.entity;

import by.inlove.shop.domain.Category;
import by.inlove.shop.domain.Image;
import by.inlove.shop.entity.CategoryEntity;
import by.inlove.shop.entity.ImageEntity;
import by.inlove.shop.transform.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryEntity2CategoryTransformer implements Transformer<CategoryEntity, Category> {
    private final Transformer<ImageEntity, Image> imageEntity2ImageTransformer;

    @Override
    public Category transform(CategoryEntity source) {
        Category target = new Category();
        target.setId(source.getId());
        target.setPriority(source.getPriority());
        target.setName(source.getName());
        target.setArchived(source.getArchived());
        target.setIcon(imageEntity2ImageTransformer.transform(source.getIcon()));
        return target;
    }
}