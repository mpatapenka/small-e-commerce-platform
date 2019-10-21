package by.underwear.shop.transform.domain;

import by.underwear.shop.domain.Category;
import by.underwear.shop.domain.Image;
import by.underwear.shop.entity.CategoryEntity;
import by.underwear.shop.entity.ImageEntity;
import by.underwear.shop.transform.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Category2CategoryEntityTransformer implements Transformer<Category, CategoryEntity> {
    private final Transformer<Image, ImageEntity> image2ImageEntityTransformer;

    @Override
    public CategoryEntity transform(Category source) {
        CategoryEntity target = new CategoryEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPriority(source.getPriority());
        target.setArchived(source.getArchived());
        target.setIcon(image2ImageEntityTransformer.transform(source.getIcon()));
        return target;
    }
}