package com.ra1n.top.eventservice.controller;

import com.ra1n.top.eventservice.model.dto.EventRequestDto;
import com.ra1n.top.eventservice.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService service) {
        this.eventService = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventRequestDto event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping
    public ResponseEntity<?> getEventById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }
}