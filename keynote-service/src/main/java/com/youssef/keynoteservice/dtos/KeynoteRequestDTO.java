package com.youssef.keynoteservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeynoteRequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private String fonction;
}
