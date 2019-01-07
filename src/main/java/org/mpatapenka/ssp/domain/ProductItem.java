package org.mpatapenka.ssp.domain;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem {
    private Long id;
    private Integer priority;
    private String name;
    private List<Size> sizes = Lists.newArrayList();
}