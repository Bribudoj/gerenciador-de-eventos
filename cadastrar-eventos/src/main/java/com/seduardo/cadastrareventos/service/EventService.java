package com.seduardo.cadastrareventos.service;

import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.exception.EventNotFoundException;
import com.seduardo.cadastrareventos.dto.mapper.mapper.EventMapper;
import com.seduardo.cadastrareventos.model.Event;
import com.seduardo.cadastrareventos.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private EventRepository eventRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;

    public EventDTO createEvent(EventDTO eventDTO) {

        Event eventToSave = eventMapper.toModel(eventDTO);

        Event savedEvent = eventRepository.save(eventToSave);

        return eventMapper.toDTO(savedEvent);
    }

    public List<EventDTO> listAll() {
        List<Event> allEvents = eventRepository.findAll();
        return allEvents.stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EventDTO findById(Long id) throws EventNotFoundException {
        Event event = verifyIfExists(id);

        return eventMapper.toDTO(event);
    }

    private Event verifyIfExists(Long id) throws EventNotFoundException {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public void deleteById(Long id) throws EventNotFoundException {
        verifyIfExists(id);
        eventRepository.deleteById(id);
    }

    public EventDTO updateById(Long id, EventDTO eventDTO) throws EventNotFoundException {
        verifyIfExists(id);

        Event eventToUpdate = eventMapper.toModel(eventDTO);

        Event updatedEvent = eventRepository.save(eventToUpdate);

        return eventMapper.toDTO(updatedEvent);
    }
}
