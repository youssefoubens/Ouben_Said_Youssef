package com.youssef.keynoteservice.services.impl;

import com.youssef.keynoteservice.dtos.KeynoteRequestDTO;
import com.youssef.keynoteservice.dtos.KeynoteResponseDTO;
import com.youssef.keynoteservice.entities.Keynote; // Assuming your entity is in 'entities' package
import com.youssef.keynoteservice.mapper.KeynoteMapper; // Assuming your mapper is in 'mappers' package
import com.youssef.keynoteservice.repositories.KeynoteRepo;
import com.youssef.keynoteservice.services.KeynoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Mark this class as a Spring Service
@AllArgsConstructor // Lombok annotation for constructor injection
public class serviceImp1 implements KeynoteService {

    private final KeynoteRepo keynoteRepo;
    private final KeynoteMapper keynoteMapper; // Inject the MapStruct mapper

    @Override
    public KeynoteResponseDTO saveKeynote(KeynoteRequestDTO keynoteRequestDTO) {
        // 1. Map DTO to Entity
        Keynote keynote = keynoteMapper.fromRequestDTO(keynoteRequestDTO);
        // 2. Save Entity to Database
        Keynote savedKeynote = keynoteRepo.save(keynote);
        // 3. Map saved Entity back to Response DTO
        return keynoteMapper.toResponseDTO(savedKeynote);
    }

    @Override
    public KeynoteResponseDTO getKeynoteById(Long id) {
        // 1. Find Entity by ID
        Keynote keynote = keynoteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Keynote not found with ID: " + id)); // Handle not found
        // 2. Map Entity to Response DTO
        return keynoteMapper.toResponseDTO(keynote);
    }

    @Override
    public List<KeynoteResponseDTO> getAllKeynotes() {
        // 1. Get all Entities
        List<Keynote> keynotes = keynoteRepo.findAll();
        // 2. Map list of Entities to list of Response DTOs
        return keynotes.stream()
                .map(keynoteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KeynoteResponseDTO updateKeynote(Long id, KeynoteRequestDTO keynoteRequestDTO) {
        // 1. Find existing Keynote
        Keynote existingKeynote = keynoteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Keynote not found with ID: " + id));
        // 2. Update existing Keynote from DTO (MapStruct handles this)
        keynoteMapper.updateKeynoteFromDto(keynoteRequestDTO, existingKeynote);
        // 3. Save the updated Keynote
        Keynote updatedKeynote = keynoteRepo.save(existingKeynote);
        // 4. Map updated Entity to Response DTO
        return keynoteMapper.toResponseDTO(updatedKeynote);
    }

    @Override
    public void deleteKeynote(Long id) {
        // 1. Check if Keynote exists (optional, but good practice)
        if (!keynoteRepo.existsById(id)) {
            throw new RuntimeException("Keynote not found with ID: " + id);
        }
        // 2. Delete the Keynote
        keynoteRepo.deleteById(id);
    }
}