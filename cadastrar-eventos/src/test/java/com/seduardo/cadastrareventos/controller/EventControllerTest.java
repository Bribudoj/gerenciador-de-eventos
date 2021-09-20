package com.seduardo.cadastrareventos.controller;

import com.seduardo.cadastrareventos.builder.EventDTOBuilder;
import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.seduardo.cadastrareventos.utils.JsonConvertionUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    private static final String EVENT_URL_PATH = "/api/v1/event";
    private static final Long EVENT_VALID_ID = 1L;
    private static final Long EVENT_INVALID_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(eventController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenABeerIsCreated() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        Mockito.when(eventService.createEvent(expectedEventDTO)).thenReturn(expectedEventDTO);

        //then
        mockMvc.perform(post(EVENT_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isCreated());
    }

}
