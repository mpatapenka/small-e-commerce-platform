package org.mpatapenka.ssp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Long id;
    private String name;
    private String downloadUrl;
    private transient Resource resource;
}