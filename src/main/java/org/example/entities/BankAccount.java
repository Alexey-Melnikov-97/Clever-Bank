package org.example.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class BankAccount {
    private final int id;
    private final int userId;
    private final int bankId;
    private Double amount;
}
