package org.mpatapenka.ssp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Category;
import org.mpatapenka.ssp.entity.CategoryEntity;
import org.mpatapenka.ssp.repository.IdRepository;
import org.mpatapenka.ssp.service.CategoryService;
import org.mpatapenka.ssp.transform.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final IdRepository<CategoryEntity> categoryRepository;
    private final Transformer<CategoryEntity, Category> categoryTransformer;

    @Override
    public Collection<Category> getAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingInt(CategoryEntity::getPriority))
                .map(categoryTransformer::forward)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Category> getAllActive() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .filter(CategoryEntity::isArchived)
                .sorted(Comparator.comparingInt(CategoryEntity::getPriority))
                .map(categoryTransformer::forward)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveAll(Collection<Category> categories) {
        Map<Long, Category> toUpdate = Maps.newLinkedHashMap();
        List<Category> toAdd = Lists.newArrayList();

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
                    .map(categoryTransformer::backward)
                    .collect(Collectors.toList()));
        }
    }

    private void updateEntity(CategoryEntity entity, Category category) {
        CategoryEntity builtInEntity = categoryTransformer.backward(category);
        entity.setName(builtInEntity.getName());
        entity.setPriority(builtInEntity.getPriority());
        entity.setIcon(builtInEntity.getIcon());
        entity.setArchived(builtInEntity.isArchived());
    }

    @Override
    @Transactional
    public void removeAll(Collection<Long> categoryIds) {
        categoryRepository.deleteAll(categoryRepository.findAllById(categoryIds));
    }
}