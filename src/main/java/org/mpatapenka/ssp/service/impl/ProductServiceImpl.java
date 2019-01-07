package org.mpatapenka.ssp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpatapenka.ssp.domain.Product;
import org.mpatapenka.ssp.repository.ProductRepository;
import org.mpatapenka.ssp.service.ProductService;
import org.mpatapenka.ssp.service.filter.ProductFilter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product get(long productId) {
        return null;
    }

    @Override
    public Collection<Product> getAll() {
        return null;
    }

    @Override
    public Collection<Product> getAll(ProductFilter filter, Sort sort) {
        return null;
    }

    @Override
    public void saveAll(Collection<Product> products) {

    }

    @Override
    public void removeAll(Collection<Long> productIds) {

    }
}