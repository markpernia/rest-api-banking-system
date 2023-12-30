package dev.markpernia.restapibankingsystem.service;

import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.entity.Customer;
import dev.markpernia.restapibankingsystem.mapper.CustomerMapper;
import dev.markpernia.restapibankingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO findCustomerById(Long id) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new Exception("no customer was found");
        }

        CustomerDTO customerDTO = customerMapper.toDTO(customer.get());

        if (customerDTO.getAccountType() != null) {
            if (isNotValidWithAccount(customerDTO)) {
                throw new Exception("customer data was invalid");
            }
        } else {
            isNotValid(customerDTO);
        }

        return customerDTO;

    }

    public void save(CustomerDTO customerDTO) throws Exception {
        if (isNotValid(customerDTO)) {
            throw new Exception("customer data was invalid");
        }

        customerRepository.save(customerMapper.toEntity(customerDTO));

    }

    public void update(Long id, CustomerDTO customerDTO) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new Exception("no customer was found");
        }

        if (isNotValid(customerDTO)) {
            throw new Exception("customer data was invalid");
        }

        customerMapper.updateToEntity(customerDTO, customer.get());
        customerRepository.save(customer.get());
    }

    // Validation
    private boolean isNotValidWithAccount(CustomerDTO customerDTO) {
        return isNullOrEmpty(customerDTO.getFirstName()) ||
                isNullOrEmpty(customerDTO.getLastName()) ||
                isInvalidEmail(customerDTO.getEmail()) ||
                isNullOrEmpty(customerDTO.getPhoneNumber()) ||
                isNullOrEmpty(customerDTO.getAccountType()) ||
                customerDTO.getBalance() == null ||
                isNullOrEmpty(customerDTO.getOpenedDate());
    }

    private boolean isNotValid(CustomerDTO customerDTO) {
        return isNullOrEmpty(customerDTO.getFirstName()) ||
                isNullOrEmpty(customerDTO.getLastName()) ||
                isInvalidEmail(customerDTO.getEmail()) ||
                isNullOrEmpty(customerDTO.getPhoneNumber());
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    private boolean isInvalidEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return !Pattern.compile(regexPattern).matcher(email).matches() || isNullOrEmpty(email);
    }
}
