package org.mpatapenka.secp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraInformation {
    private Long id;
    private String name;
    private String value;
}