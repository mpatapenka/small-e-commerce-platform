package by.inlove.shop.entity;

import by.inlove.shop.util.Comparators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "_inventory")
public class InventoryEntity extends IdentifiedEntity implements Comparable<InventoryEntity> {
    private Integer amount;

    @ManyToOne
    private ProductItemEntity productItem;

    @ManyToOne
    private SizeEntity size;

    @Override
    public int compareTo(InventoryEntity that) {
        return Comparators.SIZE_ENTITY_COMPARATOR.compare(this.size, that.size);
    }
}