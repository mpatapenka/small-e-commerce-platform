package org.mpatapenka.secp.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import org.hibernate.annotations.SortNatural;
import org.mpatapenka.secp.util.Comparators;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.SortedSet;

@Data
@Entity
@Table(name = "_product_item")
public class ProductItemEntity implements Comparable<ProductItemEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer priority;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @SortNatural
    private SortedSet<SizeEntity> sizes = Sets.newTreeSet();

    @Override
    public int compareTo(ProductItemEntity that) {
        return Comparators.NULL_LAST_INTEGER_COMPARATOR.compare(this.priority, that.priority);
    }
}