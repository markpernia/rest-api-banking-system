package dev.markpernia.restapibankingsystem.mapper;

import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.entity.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public CustomerDTO toDTO(Customer customer) {

        if (customer.getAccount() != null) {
            modelMapper.typeMap(Customer.class, CustomerDTO.class)
                    .addMapping(c -> c.getAccount().getAccountType(), CustomerDTO::setAccountType)
                    .addMapping(c -> c.getAccount().getBalance(), CustomerDTO::setBalance)
                    .addMapping(c -> c.getAccount().getOpenedDate(), CustomerDTO::setOpenedDate);
        }

        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public void updateToEntity(CustomerDTO customerDTO, Customer customer) {
        modelMapper.typeMap(CustomerDTO.class, Customer.class)
                .addMappings(c -> c.skip(Customer::setId));

        modelMapper.map(customerDTO, customer);

    }
}
