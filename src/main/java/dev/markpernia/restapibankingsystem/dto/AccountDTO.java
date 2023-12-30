package dev.markpernia.restapibankingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private Long id;
    private String accountType;
    private Double balance;
    private String openedDate;
}
