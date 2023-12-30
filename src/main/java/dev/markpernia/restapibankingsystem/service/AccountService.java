package dev.markpernia.restapibankingsystem.service;

import dev.markpernia.restapibankingsystem.dto.AccountDTO;
import dev.markpernia.restapibankingsystem.entity.Account;
import dev.markpernia.restapibankingsystem.entity.Customer;
import dev.markpernia.restapibankingsystem.mapper.AccountMapper;
import dev.markpernia.restapibankingsystem.repository.AccountRepository;
import dev.markpernia.restapibankingsystem.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository,
                          AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public void save(Long id, AccountDTO accountDTO) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new Exception("no customer was found");
        }

        if (customer.get().getAccount() != null) {
            throw new Exception("customer has already an account");
        }

        if (isInvalid(accountDTO)) {
            throw new Exception("account data was invalid");
        }

        Account account = accountMapper.toEntity(accountDTO);
        if (account == null) {
            throw new Exception("failed to create an account");
        }

        customer.get().setAccount(account);
        account.setCustomer(customer.get());
        customerRepository.save(customer.get());
    }

    public void update(Long id, AccountDTO accountDTO) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new Exception("no customer was found");
        }

        if (customer.get().getAccount() == null) {
            throw new Exception("customer does not have associated account");
        }

        accountMapper.updateToEntity(accountDTO, customer.get().getAccount());
        accountRepository.save(customer.get().getAccount());
    }

    private boolean isInvalid(AccountDTO accountDTO) {
        return isNullOrEmpty(accountDTO.getAccountType()) ||
                accountDTO.getBalance() == null;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

}
