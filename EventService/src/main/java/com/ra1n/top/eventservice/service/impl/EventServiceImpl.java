package com.ra1n.top.eventservice.service.impl;

import com.ra1n.top.eventservice.model.Event;
import com.ra1n.top.eventservice.model.dto.EventRequestDto;
import com.ra1n.top.eventservice.model.dto.EventResponseDto;
import com.ra1n.top.eventservice.repository.EventRepository;
import com.ra1n.top.eventservice.service.EventService;
import com.ra1n.top.eventservice.service.mapper.EventMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Service
@Log4j2
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper mapper = EventMapper.INSTANCE;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(EventRequestDto event) {
        return eventRepository.save(mapper.toEntity(event));
    }

    @Override
    public EventResponseDto getEvent(Long id) {
        return mapper.toDto(eventRepository.getEventById(id));
    }

    @Override
    public Event updateEvent(Long id, EventRequestDto eventRequest) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Event not found with id " + id));
        existing.setClientId(eventRequest.getClientId());
        existing.setTitle(eventRequest.getTitle());
        existing.setDescription(eventRequest.getDescription());
        existing.setEventDate(eventRequest.getEventDate());
        existing.setDuration(eventRequest.getDuration());
        existing.setNotificationLeadTime(eventRequest.getNotificationLeadTime());
        return eventRepository.save(existing);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
