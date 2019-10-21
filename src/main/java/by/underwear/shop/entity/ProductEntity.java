package by.underwear.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "_product")
public class ProductEntity extends IdentifiedEntity {
    private String name;
    private String description;
    private String fabricator;
    private String composition;
    private BigDecimal price;
    private BigDecimal salePrice;
    private Boolean archived;

    @ManyToOne
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @SortNatural
    private SortedSet<ProductItemEntity> productItems = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @SortNatural
    private SortedSet<ImageEntity> images = new TreeSet<>();
}