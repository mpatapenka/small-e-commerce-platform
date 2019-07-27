package by.inlove.shop.service.impl;

import by.inlove.shop.domain.Product;
import by.inlove.shop.entity.ProductEntity;
import by.inlove.shop.repository.ProductRepository;
import by.inlove.shop.service.ProductService;
import by.inlove.shop.service.filter.ProductFilter;
import by.inlove.shop.transform.Transformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final Transformer<ProductEntity, Product> productEntity2ProductTransformer;
    private final Transformer<Product, ProductEntity> product2ProductEntityTransformer;

    @Override
    public Optional<Product> getById(Long productId) {
        if (productId == null) {
            return Optional.empty();
        }
        return productRepository.findById(productId)
                .map(productEntity2ProductTransformer::transform);
    }

    @Override
    public Collection<Product> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productEntity2ProductTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getAll(ProductFilter filter, Sort sort) {
        return null;
    }

    @Override
    @Transactional
    public void saveAll(Collection<Product> products) {
        Map<Long, Product> toUpdate = new LinkedHashMap<>();
        List<Product> toAdd = new ArrayList<>();

        for (Product product : products) {
            if (product.getId() != null) {
                toUpdate.put(product.getId(), product);
            } else {
                toAdd.add(product);
            }
        }

        if (!toUpdate.isEmpty()) {
            productRepository.findAllById(toUpdate.keySet())
                    .forEach(entity -> updateEntity(entity, toUpdate.get(entity.getId())));
        }

        if (!toAdd.isEmpty()) {
            productRepository.saveAll(toAdd.parallelStream()
                    .map(product2ProductEntityTransformer::transform)
                    .collect(Collectors.toList()));
        }
    }

    private void updateEntity(ProductEntity entity, Product product) {
        ProductEntity builtInEntity = product2ProductEntityTransformer.transform(product);
        entity.setName(builtInEntity.getName());
        entity.setArchived(builtInEntity.getArchived());
    }

    @Override
    @Transactional
    public void removeAll(Collection<Long> productIds) {
        productRepository.deleteAll(productRepository.findAllById(productIds));
    }
}