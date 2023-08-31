package org.example.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Transactions {
    private final int id;
    private final LocalDateTime dateTime;
    private final String transaction;
    private final double amount;
}
