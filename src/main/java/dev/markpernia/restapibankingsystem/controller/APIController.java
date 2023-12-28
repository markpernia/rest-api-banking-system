package dev.markpernia.restapibankingsystem.controller;

import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.dto.ErrorDTO;
import dev.markpernia.restapibankingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
public class APIController {

    private CustomerService customerService;

    @Autowired
    public APIController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable Long customerId) throws Exception {
         try {
             CustomerDTO customerDTO = customerService.findCustomerById(customerId);
             return ResponseEntity.ok().body(customerDTO);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
         }
    }

}
