package com.intexsoft.webshop.eventbus.repository;

import com.intexsoft.webshop.eventbus.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
