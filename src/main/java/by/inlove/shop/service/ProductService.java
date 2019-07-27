package by.inlove.shop.service;

import by.inlove.shop.domain.Product;
import by.inlove.shop.service.filter.ProductFilter;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getById(Long productId);
    Collection<Product> getAll();
    Collection<Product> getAll(ProductFilter filter, Sort sort);
    void saveAll(Collection<Product> products);
    void removeAll(Collection<Long> productIds);
}