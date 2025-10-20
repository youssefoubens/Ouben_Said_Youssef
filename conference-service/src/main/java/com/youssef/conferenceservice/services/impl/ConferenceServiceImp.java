package com.youssef.conferenceservice.services.impl;

import com.youssef.conferenceservice.dtos.ConferenceRequestDTO;
import com.youssef.conferenceservice.dtos.ConferenceResponseDTO;
import com.youssef.conferenceservice.dtos.ReviewRequestDTO;
import com.youssef.conferenceservice.dtos.ReviewResponseDTO;
import com.youssef.conferenceservice.entities.Conference;
import com.youssef.conferenceservice.entities.Review;
import com.youssef.conferenceservice.mappers.ConferenceMapper;
import com.youssef.conferenceservice.mappers.ReviewMapper;
import com.youssef.conferenceservice.repositories.ConferenceRepo;
import com.youssef.conferenceservice.repositories.ReviewRepo;
import com.youssef.conferenceservice.services.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConferenceServiceImp implements ConferenceService {

    private final ConferenceRepo conferenceRepository;
    private final ReviewRepo reviewRepository;
    private final ConferenceMapper conferenceMapper;
    private final ReviewMapper reviewMapper;

    @Override
    public ConferenceResponseDTO saveConference(ConferenceRequestDTO conferenceRequestDTO) {
        Conference conference = conferenceMapper.fromRequestDTO(conferenceRequestDTO);
        Conference savedConference = conferenceRepository.save(conference);
        return conferenceMapper.toResponseDTO(savedConference);
    }

    @Override
    public ConferenceResponseDTO getConferenceById(Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found with ID: " + id));
        return conferenceMapper.toResponseDTO(conference);
    }

    @Override
    public List<ConferenceResponseDTO> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        return conferences.stream()
                .map(conferenceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO conferenceRequestDTO) {
        Conference existingConference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found with ID: " + id));
        conferenceMapper.updateConferenceFromDto(conferenceRequestDTO, existingConference);
        Conference updatedConference = conferenceRepository.save(existingConference);
        return conferenceMapper.toResponseDTO(updatedConference);
    }

    @Override
    public void deleteConference(Long id) {
        if (!conferenceRepository.existsById(id)) {
            throw new RuntimeException("Conference not found with ID: " + id);
        }
        conferenceRepository.deleteById(id);
    }

    // --- Review Management Methods ---

    @Override
    public ReviewResponseDTO addReviewToConference(Long conferenceId, ReviewRequestDTO reviewRequestDTO) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found with ID: " + conferenceId));

        Review review = reviewMapper.fromRequestDTO(reviewRequestDTO);
        review.setConference(conference); // Set the relationship
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toResponseDTO(savedReview);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByConferenceId(Long conferenceId) {
        // You might have a custom method in ReviewRepository like findByConferenceId
        // For simplicity, we'll fetch conference and then its reviews
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found with ID: " + conferenceId));
        return conference.getReviews().stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));
        return reviewMapper.toResponseDTO(review);
    }

    @Override
    public ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));

        // Ensure the conferenceId in DTO matches the existing review's conference (optional, but good practice)
        if (!existingReview.getConference().getId().equals(reviewRequestDTO.getConferenceId())) {
            throw new RuntimeException("Review's conference ID cannot be changed directly via update. Create a new review for a different conference.");
        }

        reviewMapper.updateReviewFromDto(reviewRequestDTO, existingReview);
        Review updatedReview = reviewRepository.save(existingReview);
        return reviewMapper.toResponseDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Review not found with ID: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}
