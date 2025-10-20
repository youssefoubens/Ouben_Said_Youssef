package com.youssef.conferenceservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne; // Import ManyToOne
import jakarta.persistence.JoinColumn; // Import JoinColumn
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String text;
    private Integer note; // stars (1 à 5)

    // De nombreuses critiques peuvent appartenir à une seule conférence
    @ManyToOne
    @JoinColumn(name = "conference_id") // Ce sera la colonne de clé étrangère dans la table des critiques
    private Conference conference;
}
