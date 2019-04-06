package org.mpatapenka.secp.service;

import org.mpatapenka.secp.domain.Product;
import org.mpatapenka.secp.service.filter.ProductFilter;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface ProductService {
    Product get(long productId);
    Collection<Product> getAll();
    Collection<Product> getAll(ProductFilter filter, Sort sort);
    void saveAll(Collection<Product> products);
    void removeAll(Collection<Long> productIds);
}