package org.mpatapenka.secp.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import org.hibernate.annotations.SortNatural;

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
import java.util.SortedSet;

@Data
@Entity
@Table(name = "_product")
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
    private boolean archived;

    @ManyToOne(cascade = CascadeType.ALL)
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @SortNatural
    private SortedSet<ProductItemEntity> productItems = Sets.newTreeSet();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @SortNatural
    private SortedSet<ImageEntity> images = Sets.newTreeSet();
}