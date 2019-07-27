//package by.inlove.shop.web.admin;
//
//import com.google.common.collect.Lists;
//import com.keeprise.app.pojo.dto.CategoryDto;
//import com.keeprise.app.pojo.entity.Category;
//import com.keeprise.app.pojo.transform.LayerTransformer;
//import com.keeprise.app.service.CategoryService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.validation.Valid;
//import java.util.Collection;
//import java.util.List;
//
//@Slf4j
//@Controller
//@RequestMapping("admin/category")
//@RequiredArgsConstructor
//public class CategoryManageController {
//    private final CategoryService categoryService;
//    private final LayerTransformer<Category, CategoryDto> categoryTransformer;
//
//    @GetMapping("manage")
//    public String categoryManagePage(@RequestParam(value = "category_id", required = false) Long categoryId, Model model) {
//        model.addAttribute("categoryDto", categoryService.getById(categoryId).map(categoryTransformer::toDto)
//                .orElseGet(CategoryDto::new));
//        return "admin/category-manage";
//    }
//
//    @PostMapping("manage")
//    public String saveCategory(@Valid CategoryDto categoryDto, Errors errors) {
//        log.debug("Processing category dto = {}", categoryDto);
//        if (errors.hasErrors()) {
//            return "admin/category-manage";
//        }
//        categoryService.save(categoryDto);
//        return "redirect:/admin/dashboard";
//    }
//
//    @DeleteMapping(value = "remove", params = "category_id")
//    public @ResponseBody
//    ResponseEntity<?> removeCategory(@RequestParam("category_id") Long categoryId) {
//        log.debug("Removing category with id={}", categoryId);
//        categoryService.remove(categoryId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("upload")
//    public @ResponseBody
//    ResponseEntity<?> batchUpload(@RequestBody Collection<CategoryDto> categories) {
//        log.debug("Uploading '{}' categories.", categories.size());
//        List<Category> uploadedCategories = Lists.newArrayListWithExpectedSize(categories.size());
//        for (CategoryDto categoryDto : categories) {
//            Category savedCategory = categoryService.save(categoryDto);
//            log.debug("Category uploaded: {}", savedCategory);
//            uploadedCategories.add(savedCategory);
//        }
//        return ResponseEntity.ok().body(uploadedCategories);
//    }
//}