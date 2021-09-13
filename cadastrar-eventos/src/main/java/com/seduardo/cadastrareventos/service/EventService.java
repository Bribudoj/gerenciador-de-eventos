package com.seduardo.cadastrareventos.service;

import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.dto.response.MessageResponseDTO;
import com.seduardo.cadastrareventos.mapper.EventMapper;
import com.seduardo.cadastrareventos.model.Event;
import com.seduardo.cadastrareventos.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private EventRepository eventRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;

    public MessageResponseDTO createEvent(EventDTO eventDTO) {

        Event eventToSave = eventMapper.toModel(eventDTO);

        Event savedEvent = eventRepository.save(eventToSave);

        return createMessageResponse(savedEvent.getId(), "Created event with ID");
    }
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
