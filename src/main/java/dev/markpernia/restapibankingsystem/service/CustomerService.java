package dev.markpernia.restapibankingsystem.service;

import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.entity.Customer;
import dev.markpernia.restapibankingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO findCustomerById(Long id) throws Exception {
        Optional<Customer> customer = customerRepository.findCustomerById(id);

        if (customer.isEmpty()) {
            throw new Exception("no customer was found");
        }

        // todo map customer -> customerDTO and return it
    }
}
