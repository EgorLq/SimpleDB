package org.example.struct;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Struct {
    private long account;
    private String name;
    private double value;

    @Override
    public String toString() {
        return "Акаунт: " + account + "\n" +
                "Имя: " + name + "\n" +
                "Значение: " + value + "\n" ;
    }
}

