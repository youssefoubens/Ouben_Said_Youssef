package com.youssef.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date; // Assuming you'll use java.util.Date for the date field

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRequestDTO {
    private String titre;
    private String type; // académique ou commerciale
    private Date date;
    private Double duree; // Assuming duration can be a decimal (e.g., hours)
    private Integer nombreInscrits;
    private Integer score;
    // When creating or updating a conference, you generally don't include reviews directly in the request DTO.
    // Reviews are usually added/managed through a separate endpoint or service.
}
