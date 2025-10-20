package com.youssef.conferenceservice.web;

import com.youssef.conferenceservice.dtos.ConferenceRequestDTO;
import com.youssef.conferenceservice.dtos.ConferenceResponseDTO;
import com.youssef.conferenceservice.dtos.ReviewRequestDTO;
import com.youssef.conferenceservice.dtos.ReviewResponseDTO;
import com.youssef.conferenceservice.services.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences") // Base path for conference-related endpoints
@AllArgsConstructor
public class ConferenceController {

    private ConferenceService conferenceService;

    // --- Endpoints pour les Conférences ---

    @GetMapping // GET /conferences
    public ResponseEntity<List<ConferenceResponseDTO>> getAllConferences() {
        List<ConferenceResponseDTO> conferences = conferenceService.getAllConferences();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    @GetMapping("/{id}") // GET /conferences/{id}
    public ResponseEntity<ConferenceResponseDTO> getConferenceById(@PathVariable Long id) {
        try {
            ConferenceResponseDTO conference = conferenceService.getConferenceById(id);
            return new ResponseEntity<>(conference, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping // POST /conferences
    public ResponseEntity<ConferenceResponseDTO> saveConference(@RequestBody ConferenceRequestDTO conferenceRequestDTO) {
        ConferenceResponseDTO savedConference = conferenceService.saveConference(conferenceRequestDTO);
        return new ResponseEntity<>(savedConference, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // PUT /conferences/{id}
    public ResponseEntity<ConferenceResponseDTO> updateConference(@PathVariable Long id, @RequestBody ConferenceRequestDTO conferenceRequestDTO) {
        try {
            ConferenceResponseDTO updatedConference = conferenceService.updateConference(id, conferenceRequestDTO);
            return new ResponseEntity<>(updatedConference, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // DELETE /conferences/{id}
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        try {
            conferenceService.deleteConference(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // --- Endpoints pour les Reviews associées à une Conférence ---

    @PostMapping("/{conferenceId}/reviews") // POST /conferences/{conferenceId}/reviews
    public ResponseEntity<ReviewResponseDTO> addReviewToConference(
            @PathVariable Long conferenceId,
            @RequestBody ReviewRequestDTO reviewRequestDTO) {
        try {
            ReviewResponseDTO addedReview = conferenceService.addReviewToConference(conferenceId, reviewRequestDTO);
            return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or another appropriate error status
        }
    }

    @GetMapping("/{conferenceId}/reviews") // GET /conferences/{conferenceId}/reviews
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsForConference(@PathVariable Long conferenceId) {
        try {
            List<ReviewResponseDTO> reviews = conferenceService.getReviewsByConferenceId(conferenceId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}") // GET /conferences/reviews/{reviewId} (Access review directly by its ID)
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long reviewId) {
        try {
            ReviewResponseDTO review = conferenceService.getReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}") // PUT /conferences/reviews/{reviewId}
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDTO reviewRequestDTO) {
        try {
            ReviewResponseDTO updatedReview = conferenceService.updateReview(reviewId, reviewRequestDTO);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}") // DELETE /conferences/reviews/{reviewId}
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        try {
            conferenceService.deleteReview(reviewId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
