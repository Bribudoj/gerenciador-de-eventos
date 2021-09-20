package com.seduardo.cadastrareventos.controller;

import com.seduardo.cadastrareventos.service.EventService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class EventoControllerTest {

    private static final String EVENT_URL_PATH = "/api/v1/event";
    private static final Long EVENT_VALID_ID = 1L;
    private static final Long EVENT_INVALID_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;








}
