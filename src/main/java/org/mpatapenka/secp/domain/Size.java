package org.mpatapenka.secp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private Long id;
    private Integer numeric;
    private String symbolic;
    private boolean archived;
}