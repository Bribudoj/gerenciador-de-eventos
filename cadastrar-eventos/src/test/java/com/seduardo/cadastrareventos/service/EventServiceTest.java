package com.seduardo.cadastrareventos.service;

import com.seduardo.cadastrareventos.mapper.EventMapper;
import com.seduardo.cadastrareventos.repository.EventRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    private static final long INVALID_EVENT_ID = 1L;

    @Mock
    private EventRepository eventRepository;

    private EventMapper eventMapper = EventMapper.INSTANCE;

    @InjectMocks
    private  EventService eventService;
}
