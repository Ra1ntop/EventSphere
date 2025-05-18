package com.ra1n.top.eventservice.repository;

import com.ra1n.top.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Travkin Andrii
 * @version 18.05.2025
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getEventById(Long id);
}
