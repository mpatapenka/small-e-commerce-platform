package org.mpatapenka.ssp.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Category;
import org.mpatapenka.ssp.domain.Size;
import org.mpatapenka.ssp.service.CategoryService;
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
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Collection<Category>> getCategories(@RequestParam(value = "archived", required = false) boolean archived) {
        return ResponseEntity.ok(archived ? categoryService.getAll() : categoryService.getAllActive());
    }

    @PutMapping
    public ResponseEntity<?> updateCategories(Collection<Category> categories) {
        categoryService.saveAll(categories);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeSize(@PathVariable("id") long categoryId) {
        categoryService.removeAll(Collections.singleton(categoryId));
        return ResponseEntity.ok().build();
    }
}