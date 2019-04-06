package org.mpatapenka.secp.transform.domain;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.secp.domain.Category;
import org.mpatapenka.secp.domain.Image;
import org.mpatapenka.secp.entity.CategoryEntity;
import org.mpatapenka.secp.entity.ImageEntity;
import org.mpatapenka.secp.transform.Transformer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryTransformer extends NullSafeTransformer<CategoryEntity, Category> {
    private final Transformer<ImageEntity, Image> imageTransformer;

    @Override
    Category safeForward(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setPriority(categoryEntity.getPriority());
        category.setName(categoryEntity.getName());
        category.setArchived(categoryEntity.isArchived());
        category.setIcon(imageTransformer.forward(categoryEntity.getIcon()));
        return category;
    }

    @Override
    CategoryEntity safeBackward(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setPriority(category.getPriority());
        categoryEntity.setName(category.getName());
        categoryEntity.setArchived(category.isArchived());
        categoryEntity.setIcon(imageTransformer.backward(category.getIcon()));
        return categoryEntity;
    }
}