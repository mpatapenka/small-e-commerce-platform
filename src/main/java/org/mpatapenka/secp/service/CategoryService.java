package org.mpatapenka.secp.service;

import org.mpatapenka.secp.domain.Category;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAll();
    Collection<Category> getAllActive();
    void saveAll(Collection<Category> categories);
    void removeAll(Collection<Long> categoryIds);
}