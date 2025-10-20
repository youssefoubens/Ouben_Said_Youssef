package com.youssef.keynoteservice.web;

import com.youssef.keynoteservice.dtos.KeynoteRequestDTO;
import com.youssef.keynoteservice.dtos.KeynoteResponseDTO;
import com.youssef.keynoteservice.services.KeynoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a Spring REST Controller
@RequestMapping("/keynotes") // Base path for all endpoints in this controller
@AllArgsConstructor // Lombok for constructor injection of KeynoteService
public class KeynoteController {

    private KeynoteService keynoteService;

    // GET all keynotes
    @GetMapping
    public ResponseEntity<List<KeynoteResponseDTO>> getAllKeynotes() {
        List<KeynoteResponseDTO> keynotes = keynoteService.getAllKeynotes();
        return new ResponseEntity<>(keynotes, HttpStatus.OK);
    }

    // GET keynote by ID
    @GetMapping("/{id}")
    public ResponseEntity<KeynoteResponseDTO> getKeynoteById(@PathVariable Long id) {
        try {
            KeynoteResponseDTO keynote = keynoteService.getKeynoteById(id);
            return new ResponseEntity<>(keynote, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or a custom error response
        }
    }

    // POST create a new keynote
    @PostMapping
    public ResponseEntity<KeynoteResponseDTO> saveKeynote(@RequestBody KeynoteRequestDTO keynoteRequestDTO) {
        KeynoteResponseDTO savedKeynote = keynoteService.saveKeynote(keynoteRequestDTO);
        return new ResponseEntity<>(savedKeynote, HttpStatus.CREATED);
    }

    // PUT update an existing keynote
    @PutMapping("/{id}")
    public ResponseEntity<KeynoteResponseDTO> updateKeynote(@PathVariable Long id, @RequestBody KeynoteRequestDTO keynoteRequestDTO) {
        try {
            KeynoteResponseDTO updatedKeynote = keynoteService.updateKeynote(id, keynoteRequestDTO);
            return new ResponseEntity<>(updatedKeynote, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE a keynote
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeynote(@PathVariable Long id) {
        try {
            keynoteService.deleteKeynote(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}