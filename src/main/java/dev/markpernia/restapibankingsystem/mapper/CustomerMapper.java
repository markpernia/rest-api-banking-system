package dev.markpernia.restapibankingsystem.mapper;

import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomerDTO entityToDTO(Customer customer) {
        modelMapper.typeMap(Customer.class, CustomerDTO.class)
                .addMapping(c -> c.getAccount().getAccountType(), CustomerDTO::setAccountType)
                .addMapping(c -> c.getAccount().getBalance(), CustomerDTO::setBalance)
                .addMapping(c -> c.getAccount().getOpenedDate(), CustomerDTO::setOpenedDate);

        modelMapper.validate();
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
