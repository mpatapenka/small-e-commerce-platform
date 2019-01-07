package org.mpatapenka.ssp.domain;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private String fabricator;
    private String composition;
    private BigDecimal price;
    private BigDecimal salePrice;
    private boolean archived;
    private Category category;
    private List<ProductItem> productItems = Lists.newArrayList();
    private List<Image> images = Lists.newArrayList();
}