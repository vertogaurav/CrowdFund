package com.example.CrowdFund.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double fund;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;


}
