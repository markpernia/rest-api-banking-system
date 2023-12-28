package dev.markpernia.restapibankingsystem;

import dev.markpernia.restapibankingsystem.entity.Account;
import dev.markpernia.restapibankingsystem.entity.Customer;
import dev.markpernia.restapibankingsystem.repository.AccountRepository;
import dev.markpernia.restapibankingsystem.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiBankingSystemApplication implements CommandLineRunner {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Autowired
    public RestApiBankingSystemApplication(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApiBankingSystemApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        Account account = new Account();
        account.setAccountType("Savings");
        account.setBalance(1500.00);

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("doe");
        customer.setEmail("jd@mail.com");
        customer.setPhoneNumber("789-654123");
        customer.setAccount(account);
        account.setCustomer(customer);

        customerRepository.save(customer);

    }
}
