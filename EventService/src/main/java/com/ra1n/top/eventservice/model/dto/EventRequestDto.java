package com.ra1n.top.eventservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequestDto {
    private String clientId;
    private String title;
    private String description;
    private Instant eventDate;
    private Duration duration;
    private Duration notificationLeadTime;

}
