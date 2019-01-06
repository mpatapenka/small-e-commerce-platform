package org.mpatapenka.ssp.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    private Set<ProductItem> productItems = Sets.newLinkedHashSet();
    private List<Image> images = Lists.newArrayList();
}