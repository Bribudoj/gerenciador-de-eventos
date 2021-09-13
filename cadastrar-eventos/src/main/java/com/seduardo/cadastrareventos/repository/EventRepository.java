package com.seduardo.cadastrareventos.repository;

import com.seduardo.cadastrareventos.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
