package com.youssef.conferenceservice.mappers;

import com.youssef.conferenceservice.dtos.ConferenceRequestDTO;
import com.youssef.conferenceservice.dtos.ConferenceResponseDTO;
import com.youssef.conferenceservice.entities.Conference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class}) // Use "spring" component model, and specify ReviewMapper for nested mapping
public interface ConferenceMapper {

    // You can remove this INSTANCE if you're injecting the mapper via Spring
    ConferenceMapper INSTANCE = Mappers.getMapper(ConferenceMapper.class);

    // Map ConferenceRequestDTO to Conference entity for creation
    Conference fromRequestDTO(ConferenceRequestDTO conferenceRequestDTO);

    // Map Conference entity to ConferenceResponseDTO for returning data
    // MapStruct will automatically try to map the 'reviews' list if ReviewMapper is used
    ConferenceResponseDTO toResponseDTO(Conference conference);

    // Map a list of Conference entities to a list of ConferenceResponseDTOs
    List<ConferenceResponseDTO> toResponseDTOList(List<Conference> conferences);

    // Update an existing Conference entity from ConferenceRequestDTO
    @Mapping(target = "id", ignore = true) // Ignore ID when updating from DTO
    @Mapping(target = "reviews", ignore = true) // Ignore reviews list during update from simple request DTO
    void updateConferenceFromDto(ConferenceRequestDTO conferenceRequestDTO, @MappingTarget Conference conference);
}
