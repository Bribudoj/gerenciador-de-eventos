package com.seduardo.cadastrareventos.controller;

import com.seduardo.cadastrareventos.builder.EventDTOBuilder;
import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.exception.EventNotFoundException;
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

import java.util.Collections;

import static com.seduardo.cadastrareventos.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    private static final String EVENT_URL_PATH = "/api/v1/event";
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
    void whenPOSTIsCalledThenIsCreatedIsReturned() throws Exception {
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

    @Test
    void whenPOSTIsCalledWithInvalidFieldThenAndErrorIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        expectedEventDTO.setSchedule(null);

        //then

        mockMvc.perform(post(EVENT_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        Mockito.when(eventService.findById(expectedEventDTO.getId())).thenReturn(expectedEventDTO);

        //then

        mockMvc.perform(get(EVENT_URL_PATH + '/' + expectedEventDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        Mockito.when(eventService.findById(expectedEventDTO.getId())).thenThrow(EventNotFoundException.class);

        //then
        mockMvc.perform(get(EVENT_URL_PATH + '/' + expectedEventDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        Mockito.when(eventService.listAll()).thenReturn(Collections.singletonList(expectedEventDTO));

        //then

        mockMvc.perform(get(EVENT_URL_PATH + '/' + expectedEventDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        doNothing().when(eventService).deleteById(expectedEventDTO.getId());

        //then

        mockMvc.perform(delete(EVENT_URL_PATH + '/' + expectedEventDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(EventNotFoundException.class).when(eventService).deleteById(EVENT_INVALID_ID);

        //then
        mockMvc.perform(delete(EVENT_URL_PATH + '/' + EVENT_INVALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPUTIsCalledWithAValidIdThenAThenOkStatusIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        Mockito.when(eventService.updateById(expectedEventDTO.getId(), expectedEventDTO)).thenReturn(expectedEventDTO);

        //then

        mockMvc.perform(put(EVENT_URL_PATH + '/' + expectedEventDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenPUTIsCalledWithInvalidFieldThenAnErrorIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        expectedEventDTO.setSchedule(null);

        //then
        mockMvc.perform(put(EVENT_URL_PATH + '/' + expectedEventDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPUTIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        doThrow(EventNotFoundException.class).when(eventService).updateById(EVENT_INVALID_ID, expectedEventDTO);

        //then
        mockMvc.perform(put(EVENT_URL_PATH + '/' + EVENT_INVALID_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedEventDTO)))
                .andExpect(status().isNotFound());
    }


}
