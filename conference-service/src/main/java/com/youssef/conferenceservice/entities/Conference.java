package com.youssef.conferenceservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String type; // académique ou commerciale
    private Date date;
    private Double duree;
    private Integer nombreInscrits;
    private Integer score;

    // A conference can have multiple reviews.
    // 'mappedBy' indicates that the 'conference' field in the Review entity is the owner of the relationship.
    @OneToMany(mappedBy = "conference")
    private List<Review> reviews = new ArrayList<>();


}
