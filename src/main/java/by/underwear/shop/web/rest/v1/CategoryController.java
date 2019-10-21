package by.underwear.shop.web.rest.v1;

import by.underwear.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import by.underwear.shop.domain.Category;
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
    public void updateCategories(Collection<Category> categories) {
        categoryService.saveAll(categories);
    }

    @DeleteMapping("{id}")
    public void removeSize(@PathVariable("id") long categoryId) {
        categoryService.removeAll(Collections.singleton(categoryId));
    }
}