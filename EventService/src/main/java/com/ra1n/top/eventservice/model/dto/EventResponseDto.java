package com.ra1n.top.eventservice.model.dto;

import java.time.LocalDate;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */

public class EventResponseDto {
    private Long id;
    private String name;
    private String location;
    private LocalDate date;

    public EventResponseDto(Long id, String name, String location, LocalDate date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public EventResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
