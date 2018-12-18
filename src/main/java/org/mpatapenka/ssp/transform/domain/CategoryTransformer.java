package org.mpatapenka.ssp.transform.domain;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Category;
import org.mpatapenka.ssp.domain.Image;
import org.mpatapenka.ssp.entity.CategoryEntity;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.mpatapenka.ssp.transform.Transformer;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
@RequiredArgsConstructor
public class CategoryTransformer extends NullSafeTransformer<CategoryEntity, Category> {
    private final Transformer<ImageEntity, Image> imageTransformer;

    @Override
    Category safeForward(@Nonnull CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setPriority(categoryEntity.getPriority());
        category.setName(categoryEntity.getName());
        category.setIcon(imageTransformer.forward(categoryEntity.getIcon()));
        return category;
    }

    @Override
    CategoryEntity safeBackward(@Nonnull Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setPriority(category.getPriority());
        categoryEntity.setName(category.getName());
        categoryEntity.setIcon(imageTransformer.backward(category.getIcon()));
        return categoryEntity;
    }
}