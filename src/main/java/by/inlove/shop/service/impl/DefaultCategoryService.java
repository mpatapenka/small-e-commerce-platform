package by.inlove.shop.service.impl;

import by.inlove.shop.domain.Category;
import by.inlove.shop.entity.CategoryEntity;
import by.inlove.shop.repository.CategoryRepository;
import by.inlove.shop.service.CategoryService;
import by.inlove.shop.transform.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Transformer<CategoryEntity, Category> categoryEntity2CategoryTransformer;
    private final Transformer<Category, CategoryEntity> category2CategoryEntityTransformer;

    @Override
    public Optional<Category> getById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return categoryRepository.findById(id)
                .map(categoryEntity2CategoryTransformer::transform);
    }

    @Override
    public Collection<Category> getAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .sorted(Comparator.nullsLast(Comparator.comparing(CategoryEntity::getPriority)))
                .map(categoryEntity2CategoryTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Category> getAllActive() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .filter(CategoryEntity::getArchived)
                .sorted(Comparator.nullsLast(Comparator.comparing(CategoryEntity::getPriority)))
                .map(categoryEntity2CategoryTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveAll(Collection<Category> categories) {
        Map<Long, Category> toUpdate = new LinkedHashMap<>();
        List<Category> toAdd = new ArrayList<>();

        for (Category category : categories) {
            if (category.getId() != null) {
                toUpdate.put(category.getId(), category);
            } else {
                toAdd.add(category);
            }
        }

        if (!toUpdate.isEmpty()) {
            categoryRepository.findAllById(toUpdate.keySet())
                    .forEach(entity -> updateEntity(entity, toUpdate.get(entity.getId())));
        }

        if (!toAdd.isEmpty()) {
            categoryRepository.saveAll(toAdd.parallelStream()
                    .map(category2CategoryEntityTransformer::transform)
                    .collect(Collectors.toList()));
        }
    }

    private void updateEntity(CategoryEntity entity, Category category) {
        CategoryEntity builtInEntity = category2CategoryEntityTransformer.transform(category);
        entity.setPriority(builtInEntity.getPriority());
        entity.setName(builtInEntity.getName());
        entity.setArchived(builtInEntity.getArchived());
        entity.setIcon(builtInEntity.getIcon());
    }

    @Override
    @Transactional
    public void removeAll(Collection<Long> categoryIds) {
        categoryRepository.deleteAll(categoryRepository.findAllById(categoryIds));
    }
}