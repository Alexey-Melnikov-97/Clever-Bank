package org.example;

import lombok.*;

@Getter
public class Bank {
    private final String name;
    public Bank(String name) {
        this.name = name;
    }
}
