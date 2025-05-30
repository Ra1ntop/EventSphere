package com.ra1n.top.eventservice.service;

import com.ra1n.top.eventservice.model.Event;
import com.ra1n.top.eventservice.model.dto.EventRequestDto;
import com.ra1n.top.eventservice.model.dto.EventResponseDto;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */

public interface EventService {
    Event createEvent(EventRequestDto event);

    EventResponseDto getEvent(Long id);

    Event updateEvent(Long id, EventRequestDto event);

    void deleteEvent(Long id);
}
