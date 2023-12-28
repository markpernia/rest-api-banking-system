package dev.markpernia.restapibankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountType;
    private Double balance;
    private final LocalDate openedDate = LocalDate.now();

    @OneToOne
    private Customer customer;
}
