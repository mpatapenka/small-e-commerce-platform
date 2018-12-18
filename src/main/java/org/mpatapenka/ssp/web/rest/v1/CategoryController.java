package org.mpatapenka.ssp.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Category;
import org.mpatapenka.ssp.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Collection<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}