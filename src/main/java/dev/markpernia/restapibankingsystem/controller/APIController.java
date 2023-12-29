package dev.markpernia.restapibankingsystem.controller;

import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.dto.ErrorDTO;
import dev.markpernia.restapibankingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
             return ResponseEntity.ok().body(customerService.findCustomerById(customerId));
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
         }
    }

    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.save(customerDTO);
            return ResponseEntity.created(URI.create("/")).body(customerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId,
                                            @RequestBody CustomerDTO customerDTO) {
        try {
            customerService.update(customerId, customerDTO);
            return ResponseEntity.ok().body(customerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

}
