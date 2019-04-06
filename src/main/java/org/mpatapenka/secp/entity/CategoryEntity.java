package org.mpatapenka.secp.entity;

import lombok.Data;
import org.mpatapenka.secp.util.Comparators;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "_category")
public class CategoryEntity implements Comparable<CategoryEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer priority;
    private String name;
    private boolean archived;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity icon;

    @Override
    public int compareTo(CategoryEntity that) {
        return Comparators.NULL_LAST_INTEGER_COMPARATOR.compare(this.priority, that.priority);
    }
}