package by.inlove.shop.entity;

import by.inlove.shop.util.Comparators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SortNatural;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "_product_item")
public class ProductItemEntity extends IdentifiedEntity implements Comparable<ProductItemEntity> {
    private Integer priority;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @SortNatural
    private SortedSet<InventoryEntity> inventories = new TreeSet<>();

    @Override
    public int compareTo(ProductItemEntity that) {
        return Comparators.NULL_LAST_INTEGER_COMPARATOR.compare(this.priority, that.priority);
    }
}