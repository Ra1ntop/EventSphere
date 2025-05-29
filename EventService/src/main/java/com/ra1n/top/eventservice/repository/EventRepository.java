package com.ra1n.top.eventservice.repository;

import com.ra1n.top.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getEventById(Long id);

    @Query(value = "SELECT * FROM event " +
            "WHERE abs(extract(epoch from (event_date - (notification_lead_time * interval '1 second'))) - extract(epoch from now())) <= :threshold " +
            "AND event_date > now()", nativeQuery = true)
    List<Event> findDueEvents(@Param("threshold") long thresholdSeconds);

}
