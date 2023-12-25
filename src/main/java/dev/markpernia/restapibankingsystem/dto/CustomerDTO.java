package dev.markpernia.restapibankingsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String accountType;
    private Double balance;
    private String openedDate;
}
