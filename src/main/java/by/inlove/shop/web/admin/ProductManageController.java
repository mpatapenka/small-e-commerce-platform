//package by.inlove.shop.web.admin;
//
//import com.google.common.collect.Lists;
//import com.keeprise.app.pojo.dto.ProductDto;
//import com.keeprise.app.pojo.entity.Category;
//import com.keeprise.app.pojo.entity.Product;
//import com.keeprise.app.pojo.transform.LayerTransformer;
//import com.keeprise.app.service.CategoryService;
//import com.keeprise.app.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
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
//@RequestMapping("admin/product")
//@RequiredArgsConstructor
//public class ProductManageController {
//    private final ProductService productService;
//    private final CategoryService categoryService;
//    private final LayerTransformer<Product, ProductDto> productTransformer;
//
//    @ModelAttribute("categories")
//    public Collection<Category> getCategories() {
//        log.debug("Categories attribute");
//        return categoryService.getAll();
//    }
//
//    @GetMapping("manage")
//    public String productManagePage(@RequestParam(value = "product_id", required = false) Long productId, Model model) {
//        model.addAttribute("productDto", productService.getById(productId).map(productTransformer::toDto)
//                .orElseGet(ProductDto::new));
//        return "admin/product-manage";
//    }
//
//    @PostMapping("manage")
//    public String saveProduct(@Valid ProductDto productDto, Errors errors) {
//        log.debug("Processing product dto = {}", productDto);
//        if (errors.hasErrors()) {
//            return "admin/product-manage";
//        }
//        productService.save(productDto);
//        return "redirect:/admin/dashboard";
//    }
//
//    @DeleteMapping(value = "remove", params = "product_id")
//    public @ResponseBody
//    ResponseEntity<?> removeProduct(@RequestParam("product_id") Long productId) {
//        log.debug("Removing product with id={}", productId);
//        productService.remove(productId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("upload")
//    public @ResponseBody
//    ResponseEntity<?> batchUpload(@RequestBody Collection<ProductDto> products) {
//        log.debug("Uploading '{}' products.", products.size());
//        List<Product> uploadedProducts = Lists.newArrayListWithExpectedSize(products.size());
//        for (ProductDto product : products) {
//            Product savedProduct = productService.save(product);
//            log.debug("Product uploaded: {}", savedProduct);
//            uploadedProducts.add(savedProduct);
//        }
//        return ResponseEntity.ok().body(uploadedProducts);
//    }
//}