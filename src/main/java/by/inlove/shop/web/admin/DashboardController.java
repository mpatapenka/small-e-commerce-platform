package by.inlove.shop.web.admin;

import by.inlove.shop.domain.Category;
import by.inlove.shop.domain.Product;
import by.inlove.shop.service.CategoryService;
import by.inlove.shop.service.ProductService;
import by.inlove.shop.service.filter.ProductFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class DashboardController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @ModelAttribute("categories")
    public Collection<Category> getCategories() {
        log.trace("DashboardController#getCategories");
        return categoryService.getAll();
    }

    @ModelAttribute("currentCategory")
    public Category getCurrentCategory(@RequestParam(value = "category_id", required = false) Long categoryId) {
        log.trace("DashboardController#getCurrentCategory");
        log.debug("Current category id '{}'", categoryId);
        return categoryService.getById(categoryId).orElse(null);
    }

    @ModelAttribute("products")
    public Collection<Product> getProducts(@ModelAttribute("currentCategory") Category category) {
        log.trace("DashboardController#getProducts(category)");
        log.debug("Products for category '{}'", category);
        if (category != null) {
            ProductFilter filter = new ProductFilter();
            filter.setCategoryId(category.getId());
            return productService.getAll(filter, Sort.unsorted());
        }
        return productService.getAll();
    }

    @GetMapping("login")
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping({"", "dashboard"})
    public String dashboardPage() {
        return "admin/dashboard";
    }
}