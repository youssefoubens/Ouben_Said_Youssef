package com.youssef.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List; // For list of reviews

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceResponseDTO {
    private Long id;
    private String titre;
    private String type; // académique ou commerciale
    private Date date;
    private Double duree;
    private Integer nombreInscrits;
    private Integer score;
    private List<ReviewResponseDTO> reviews; // Include a list of ReviewResponseDTOs if you want to embed them
}
