package com.youssef.conferenceservice.config;

import com.youssef.conferenceservice.dtos.ConferenceRequestDTO;
import com.youssef.conferenceservice.dtos.ConferenceResponseDTO;
import com.youssef.conferenceservice.dtos.ReviewRequestDTO;
import com.youssef.conferenceservice.services.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.time.Instant; // Pour des dates plus modernes et faciles à manipuler

@Configuration
@AllArgsConstructor
public class DataInitialiser {

    private ConferenceService conferenceService; // Injectez votre ConferenceService

    @Bean
    public CommandLineRunner initConferenceData() {
        return args -> {
            System.out.println("Initialisation des données de conférence...");

            // Création de quelques conférences
            ConferenceRequestDTO conf1Request = new ConferenceRequestDTO(
                    "Introduction au Machine Learning",
                    "académique",
                    Date.from(Instant.parse("2025-11-15T09:00:00Z")), // Date en novembre 2025
                    2.5, // Durée en heures
                    120,
                    4
            );
            ConferenceResponseDTO savedConf1 = conferenceService.saveConference(conf1Request);
            System.out.println("Conférence 1 enregistrée: " + savedConf1.getTitre());


            ConferenceRequestDTO conf2Request = new ConferenceRequestDTO(
                    "Développement Web avec Spring Boot et Angular",
                    "commerciale",
                    Date.from(Instant.parse("2025-12-01T10:30:00Z")), // Date en décembre 2025
                    3.0,
                    200,
                    5
            );
            ConferenceResponseDTO savedConf2 = conferenceService.saveConference(conf2Request);
            System.out.println("Conférence 2 enregistrée: " + savedConf2.getTitre());


            ConferenceRequestDTO conf3Request = new ConferenceRequestDTO(
                    "Sécurité des Micro-services avec Keycloak",
                    "académique",
                    Date.from(Instant.parse("2026-01-20T14:00:00Z")), // Date en janvier 2026
                    1.5,
                    80,
                    3
            );
            ConferenceResponseDTO savedConf3 = conferenceService.saveConference(conf3Request);
            System.out.println("Conférence 3 enregistrée: " + savedConf3.getTitre());


            // Ajout de quelques revues aux conférences
            ReviewRequestDTO review1Conf1 = new ReviewRequestDTO(
                    Date.from(Instant.now().minusSeconds(86400 * 5)), // Il y a 5 jours
                    "Conférence très instructive et bien présentée.",
                    5,
                    savedConf1.getId()
            );
            conferenceService.addReviewToConference(savedConf1.getId(), review1Conf1);

            ReviewRequestDTO review2Conf1 = new ReviewRequestDTO(
                    Date.from(Instant.now().minusSeconds(86400 * 2)), // Il y a 2 jours
                    "J'ai beaucoup appris, mais le son était parfois difficile à entendre.",
                    4,
                    savedConf1.getId()
            );
            conferenceService.addReviewToConference(savedConf1.getId(), review2Conf1);

            ReviewRequestDTO review1Conf2 = new ReviewRequestDTO(
                    Date.from(Instant.now().minusSeconds(86400 * 3)), // Il y a 3 jours
                    "Excellente couverture des sujets, très pratique.",
                    5,
                    savedConf2.getId()
            );
            conferenceService.addReviewToConference(savedConf2.getId(), review1Conf2);

            System.out.println("Données initiales de conférence et de revue chargées.");
        };
    }
}
