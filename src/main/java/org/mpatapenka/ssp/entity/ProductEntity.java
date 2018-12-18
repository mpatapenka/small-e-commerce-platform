package org.mpatapenka.ssp.entity;

import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "_PRODUCT")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String fabricator;
    private String composition;
    private BigDecimal price;
    private BigDecimal salePrice;
    private Boolean isArchived;

    @ManyToOne(cascade = CascadeType.ALL)
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductItemEntity> productItems = Lists.newArrayList();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageEntity> images = Lists.newArrayList();
}