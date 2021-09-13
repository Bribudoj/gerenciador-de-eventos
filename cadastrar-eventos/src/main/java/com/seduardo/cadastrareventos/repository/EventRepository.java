package com.seduardo.cadastrareventos.repository;

import com.seduardo.cadastrareventos.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
