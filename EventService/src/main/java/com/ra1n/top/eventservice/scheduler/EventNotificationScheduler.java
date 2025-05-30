package com.ra1n.top.eventservice.scheduler;

import com.ra1n.top.eventservice.model.Event;
import com.ra1n.top.eventservice.repository.EventRepository;
import com.ra1n.top.eventservice.service.mapper.EventMapper;
import com.ra1n.top.eventservice.model.dto.EventResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Periodically scans for events nearing their scheduled time and publishes notifications to Kafka.
 */
@Component
@RequiredArgsConstructor
public class EventNotificationScheduler {
    private final EventRepository eventRepository;
    private final KafkaTemplate<String, EventResponseDto> kafkaTemplate;
    private final EventMapper mapper = EventMapper.INSTANCE;

    @Value("${event.notification.topic}")
    private String notificationTopic;

    @Value("${eventservice.scheduler.rate:60000}")
    private long schedulerRateMs;

    /**
     * Runs at a fixed interval to send upcoming event notifications.
     */
    @Scheduled(fixedRateString = "${eventservice.scheduler.rate:60000}")
    public void notifyUpcomingEvents() {
        long thresholdSeconds = schedulerRateMs / 1000;
        List<Event> events = eventRepository.findDueEvents(thresholdSeconds);
        for (Event event : events) {
            EventResponseDto dto = mapper.toDto(event);
            kafkaTemplate.send(notificationTopic, event.getClientId(), dto);
        }
    }
}