package by.underwear.shop.service;

import by.underwear.shop.domain.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> getById(Long id);
    Collection<Category> getAll();
    Collection<Category> getAllActive();
    void saveAll(Collection<Category> categories);
    void removeAll(Collection<Long> categoryIds);
}