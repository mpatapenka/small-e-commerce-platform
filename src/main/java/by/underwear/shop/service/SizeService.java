package by.underwear.shop.service;

import by.underwear.shop.domain.Size;

import java.util.Collection;

public interface SizeService {
    Collection<Size> getAll();
    Collection<Size> getAll(long categoryId);
    Collection<Size> getAllActive();
    void saveAll(Collection<Size> sizes);
    void removeAll(Collection<Long> sizeIds);
}