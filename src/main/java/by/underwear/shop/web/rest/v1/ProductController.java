package by.underwear.shop.web.rest.v1;

import by.underwear.shop.service.ProductService;
import by.underwear.shop.service.filter.ProductFilter;
import lombok.RequiredArgsConstructor;
import by.underwear.shop.domain.Product;
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
        return ResponseEntity.ok(productService.getById(productId).orElseThrow(RuntimeException::new));
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