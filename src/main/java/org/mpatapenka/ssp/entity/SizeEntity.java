package org.mpatapenka.ssp.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import org.mpatapenka.ssp.util.Comparators;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "_size")
public class SizeEntity implements Comparable<SizeEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeric;
    private String symbolic;
    private boolean archived;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<CategoryEntity> categories = Sets.newHashSet();

    @Override
    public int compareTo(SizeEntity that) {
        return Comparators.SIZE_ENTITY_COMPARATOR.compare(this, that);
    }
}