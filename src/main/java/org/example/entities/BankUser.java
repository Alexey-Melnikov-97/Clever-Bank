package org.example.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class BankUser {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
}
