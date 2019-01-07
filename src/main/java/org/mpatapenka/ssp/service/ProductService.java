package org.mpatapenka.ssp.service;

import org.mpatapenka.ssp.domain.Product;
import org.mpatapenka.ssp.service.filter.ProductFilter;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface ProductService {
    Product get(long productId);
    Collection<Product> getAll();
    Collection<Product> getAll(ProductFilter filter, Sort sort);
    void saveAll(Collection<Product> products);
    void removeAll(Collection<Long> productIds);
}