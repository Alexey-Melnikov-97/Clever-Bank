package org.example;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Person {
    private String username;
    private String password;
}
