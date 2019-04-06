package org.mpatapenka.secp.entity;

import lombok.Data;
import org.mpatapenka.secp.util.Comparators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "_image")
public class ImageEntity implements Comparable<ImageEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String originalName;

    @Override
    public int compareTo(ImageEntity that) {
        return Comparators.NULL_LAST_STRING_COMPARATOR.compare(this.originalName, that.originalName);
    }
}