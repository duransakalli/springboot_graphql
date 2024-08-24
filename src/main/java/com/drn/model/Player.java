package com.drn.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Player {
    private Integer id;
    private String firstName;
    private String lastName;
    private Team team;
}
