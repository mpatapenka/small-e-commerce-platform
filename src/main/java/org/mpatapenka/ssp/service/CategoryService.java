package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.domain.Category;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAll();
    Collection<Category> getAllActive();
    void saveAll(Collection<Category> categories);
    void removeAll(Collection<Long> categoryIds);
}