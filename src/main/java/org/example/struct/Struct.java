package org.example.struct;

import lombok.*;


@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Struct {
    private long account;
    private String name;
    private double value;

}

