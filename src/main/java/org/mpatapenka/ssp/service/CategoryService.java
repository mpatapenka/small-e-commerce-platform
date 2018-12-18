package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.domain.Category;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAll();
    void save(Iterable<Category> categories);
    void remove(long categoryId);
}