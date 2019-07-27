package by.inlove.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal salePrice;
    private Long categoryId;
    private Boolean archived;
    private List<ExtraInformation> extraInformation = new ArrayList<>();
    private List<ProductItem> productItems = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
}