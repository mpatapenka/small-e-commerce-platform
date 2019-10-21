package by.underwear.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "_size")
public class SizeEntity extends IdentifiedEntity {
    private Integer numeric;
    private String symbolic;
    private Boolean archived;

    @ManyToMany
    private Set<CategoryEntity> categories = new HashSet<>();
}