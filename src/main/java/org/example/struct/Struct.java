package org.example.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Struct {
    private long account;
    private String name;
    private double value;

    public Struct(Long account, String name, Double value) {
        this.account = account;
        this.name = name;
        this.value = value;
    }


    public long getAccount() {
        return account;
    }


    public void setAccount(long account) {
        this.account = account;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public double getValue() {
        return value;
    }


    public void setValue(double value) {
        this.value = value;
    }


}

