package com.ra1n.top.eventservice.model.dto;

import java.time.LocalDate;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
public class EventRequestDto {
    private String name;
    private String location;
    private LocalDate date;

    public EventRequestDto(String name, String location, LocalDate date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public EventRequestDto() {
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
