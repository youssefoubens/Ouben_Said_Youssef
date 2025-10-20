package com.youssef.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    private Date date;
    private String text;
    private Integer note; // stars (1 à 5)
    private Long conferenceId; // To associate the review with a specific conference upon creation/update
}