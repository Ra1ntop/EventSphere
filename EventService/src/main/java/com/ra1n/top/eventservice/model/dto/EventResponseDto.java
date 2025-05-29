package com.ra1n.top.eventservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private Long id;
    private String clientId;
    private String title;
    private String description;
    private Instant eventDate;
    private Duration duration;
    private Duration notificationLeadTime;
    private Instant createdAt;
    private Instant updatedAt;

}
