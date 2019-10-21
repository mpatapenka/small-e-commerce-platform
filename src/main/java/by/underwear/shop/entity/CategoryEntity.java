package by.underwear.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "_category")
public class CategoryEntity extends IdentifiedEntity {
    private Integer priority;
    private String name;
    private Boolean archived;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity icon;
}