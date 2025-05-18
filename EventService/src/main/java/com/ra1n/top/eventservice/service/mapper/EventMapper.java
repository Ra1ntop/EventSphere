package com.ra1n.top.eventservice.service.mapper;

import com.ra1n.top.eventservice.model.Event;
import com.ra1n.top.eventservice.model.dto.EventRequestDto;
import com.ra1n.top.eventservice.model.dto.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(EventRequestDto dto);

    EventResponseDto toDto(Event event);

    List<EventResponseDto> toDtoList(List<Event> events);
}
