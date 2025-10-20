package com.youssef.keynoteservice.mapper;


import com.youssef.keynoteservice.dtos.KeynoteRequestDTO;
import com.youssef.keynoteservice.dtos.KeynoteResponseDTO;
import com.youssef.keynoteservice.entities.Keynote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KeynoteMapper {

    KeynoteMapper INSTANCE = Mappers.getMapper(KeynoteMapper.class);

    Keynote fromRequestDTO(KeynoteRequestDTO keynoteRequestDTO);

    KeynoteResponseDTO toResponseDTO(Keynote keynote);

    @Mapping(target = "id", ignore = true)
    void updateKeynoteFromDto(KeynoteRequestDTO keynoteRequestDTO, @MappingTarget Keynote keynote);
}
