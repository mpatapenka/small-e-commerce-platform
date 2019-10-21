package by.underwear.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private Integer priority;
    private String name;
    private Boolean archived;
    private Image icon;
}