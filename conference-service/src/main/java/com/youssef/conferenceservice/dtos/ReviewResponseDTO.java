package com.youssef.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private Date date;
    private String text;
    private Integer note; // stars (1 à 5)
    private Long conferenceId; // To show which conference this review belongs to
    // You might also include a simplified ConferenceDTO if you need some conference details here,
    // but usually, just the ID is sufficient or you fetch the conference separately.
}
