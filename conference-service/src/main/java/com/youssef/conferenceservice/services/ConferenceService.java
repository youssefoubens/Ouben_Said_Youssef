package com.youssef.conferenceservice.services;

import com.youssef.conferenceservice.dtos.ConferenceRequestDTO;
import com.youssef.conferenceservice.dtos.ConferenceResponseDTO;
import com.youssef.conferenceservice.dtos.ReviewRequestDTO;
import com.youssef.conferenceservice.dtos.ReviewResponseDTO;

import java.util.List;

public interface ConferenceService {
    ConferenceResponseDTO saveConference(ConferenceRequestDTO conferenceRequestDTO);
    ConferenceResponseDTO getConferenceById(Long id);
    List<ConferenceResponseDTO> getAllConferences();
    ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO conferenceRequestDTO);
    void deleteConference(Long id);

    // Methods for managing reviews associated with a conference
    ReviewResponseDTO addReviewToConference(Long conferenceId, ReviewRequestDTO reviewRequestDTO);
    List<ReviewResponseDTO> getReviewsByConferenceId(Long conferenceId);
    ReviewResponseDTO getReviewById(Long reviewId);
    ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO);
    void deleteReview(Long reviewId);
}