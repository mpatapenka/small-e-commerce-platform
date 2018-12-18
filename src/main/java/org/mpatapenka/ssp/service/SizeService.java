package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.domain.Size;

import java.util.Collection;

public interface SizeService {
    Collection<Size> getAll();
    Collection<Size> getAll(long categoryId);
    void archive(long sizeId);
    void remove(long sizeId);
}