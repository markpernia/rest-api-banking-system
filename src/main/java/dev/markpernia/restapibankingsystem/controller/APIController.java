package dev.markpernia.restapibankingsystem.controller;

import dev.markpernia.restapibankingsystem.dto.AccountDTO;
import dev.markpernia.restapibankingsystem.dto.CustomerDTO;
import dev.markpernia.restapibankingsystem.dto.ErrorDTO;
import dev.markpernia.restapibankingsystem.repository.AccountRepository;
import dev.markpernia.restapibankingsystem.service.AccountService;
import dev.markpernia.restapibankingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/customers")
public class APIController {

    private CustomerService customerService;
    private AccountService accountService;

    @Autowired
    public APIController(CustomerService customerService,
                         AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
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

    @PostMapping("{customerId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId,
                                           @RequestBody AccountDTO accountDTO) {
        try {
            accountService.save(customerId, accountDTO);
            return ResponseEntity.created(URI.create("/")).body(accountDTO);
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/accounts/{customerId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long customerId,
                                           @RequestBody AccountDTO accountDTO) {
        try {
            accountService.update(customerId, accountDTO);
            return ResponseEntity.ok().body(accountDTO);
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

}
