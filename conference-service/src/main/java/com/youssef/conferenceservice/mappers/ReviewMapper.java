package com.youssef.conferenceservice.mappers;


import com.youssef.conferenceservice.dtos.ReviewResponseDTO;
import com.youssef.conferenceservice.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") // Use "spring" component model for Spring integration
public interface ReviewMapper {

    // You can remove this INSTANCE if you're injecting the mapper via Spring
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    // Map ReviewRequestDTO to Review entity for creation
    // We need to map conferenceId to a Conference object.
    // This assumes you'll fetch the Conference entity in your service layer
    // before mapping, or you can add a custom method for this.
    @Mapping(target = "conference", ignore = true) // Conference will be set in the service
    Review fromRequestDTO(ReviewRequestDTO reviewRequestDTO);

    // Map Review entity to ReviewResponseDTO for returning data
    @Mapping(source = "conference.id", target = "conferenceId") // Map conference.id from entity to conferenceId in DTO
    ReviewResponseDTO toResponseDTO(Review review);

    // Map a list of Review entities to a list of ReviewResponseDTOs
    List<ReviewResponseDTO> toResponseDTOList(List<Review> reviews);


    // Update an existing Review entity from ReviewRequestDTO
    @Mapping(target = "id", ignore = true) // Ignore ID when updating from DTO
    @Mapping(target = "conference", ignore = true) // Conference will be handled in the service
    void updateReviewFromDto(ReviewRequestDTO reviewRequestDTO, @MappingTarget Review review);
}
