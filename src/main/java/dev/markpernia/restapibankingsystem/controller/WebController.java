package dev.markpernia.restapibankingsystem.controller;

import dev.markpernia.restapibankingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final CustomerRepository customerRepository;

    @Autowired
    public WebController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }
}
