package by.underwear.shop.entity;

import by.underwear.shop.util.Comparators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "_image")
public class ImageEntity extends IdentifiedEntity implements Comparable<ImageEntity> {
    private String name;
    private String path;
    private String url;

    @Override
    public int compareTo(ImageEntity that) {
        return Comparators.NULL_LAST_STRING_COMPARATOR.compare(this.name, that.name);
    }
}