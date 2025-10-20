package com.youssef.keynoteservice.config;

import com.youssef.keynoteservice.dtos.KeynoteRequestDTO;
import com.youssef.keynoteservice.services.KeynoteService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DataInitialiser {

    private KeynoteService keynoteService; // Inject your KeynoteService

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Add some initial keynote data for testing
            System.out.println("Initializing Keynote data...");
            keynoteService.saveKeynote(new KeynoteRequestDTO("John", "Doe", "john.doe@example.com", "CEO"));
            keynoteService.saveKeynote(new KeynoteRequestDTO("Jane", "Smith", "jane.smith@example.com", "CTO"));
            keynoteService.saveKeynote(new KeynoteRequestDTO("Peter", "Jones", "peter.jones@example.com", "Developer"));
            keynoteService.saveKeynote(new KeynoteRequestDTO("Alice", "Brown", "alice.brown@example.com", "Marketing Manager"));
            keynoteService.saveKeynote(new KeynoteRequestDTO("Bob", "White", "bob.white@example.com", "Data Scientist"));

            System.out.println("Initial Keynote data loaded.");
        };
    }
}