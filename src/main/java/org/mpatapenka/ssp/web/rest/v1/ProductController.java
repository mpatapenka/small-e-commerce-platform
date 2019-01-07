package org.mpatapenka.ssp.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Product;
import org.mpatapenka.ssp.service.ProductService;
import org.mpatapenka.ssp.service.filter.ProductFilter;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Collection<Product>> getProducts(@RequestParam ProductFilter filter, Sort sort) {
        return ResponseEntity.ok(productService.getAll(filter, sort));
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long productId) {
        return ResponseEntity.ok(productService.get(productId));
    }

    @PutMapping
    public void updateProducts(Collection<Product> products) {
        productService.saveAll(products);
    }

    @DeleteMapping("{id}")
    public void removeProduct(@PathVariable("id") long productId) {
        productService.removeAll(Collections.singleton(productId));
    }
}