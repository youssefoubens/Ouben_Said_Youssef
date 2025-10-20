package com.youssef.keynoteservice.services;

import com.youssef.keynoteservice.dtos.KeynoteRequestDTO;
import com.youssef.keynoteservice.dtos.KeynoteResponseDTO;

import java.util.List;

public interface KeynoteService {
    KeynoteResponseDTO saveKeynote(KeynoteRequestDTO keynoteRequestDTO);
    KeynoteResponseDTO getKeynoteById(Long id);
    List<KeynoteResponseDTO> getAllKeynotes();
    KeynoteResponseDTO updateKeynote(Long id, KeynoteRequestDTO keynoteRequestDTO);
    void deleteKeynote(Long id);
}