package dev.markpernia.restapibankingsystem.mapper;

import dev.markpernia.restapibankingsystem.dto.AccountDTO;
import dev.markpernia.restapibankingsystem.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account toEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }
}
