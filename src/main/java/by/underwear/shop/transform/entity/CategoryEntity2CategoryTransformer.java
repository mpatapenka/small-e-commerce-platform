package by.underwear.shop.transform.entity;

import by.underwear.shop.domain.Category;
import by.underwear.shop.domain.Image;
import by.underwear.shop.entity.CategoryEntity;
import by.underwear.shop.entity.ImageEntity;
import by.underwear.shop.transform.Transformer;
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