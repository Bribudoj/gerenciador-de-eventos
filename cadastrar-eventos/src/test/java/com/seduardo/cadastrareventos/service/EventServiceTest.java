package com.seduardo.cadastrareventos.service;

import com.seduardo.cadastrareventos.builder.EventDTOBuilder;
import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.dto.mapper.mapper.EventMapper;
import com.seduardo.cadastrareventos.model.Event;
import com.seduardo.cadastrareventos.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    private static final long INVALID_EVENT_ID = 1L;

    @Mock
    private EventRepository eventRepository;

    private EventMapper eventMapper = EventMapper.INSTANCE;

    @InjectMocks
    private  EventService eventService;

    @Test
    void whenEventInformedThenItShouldBeCreated() {
        // given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        Event expectedSavedEvent = eventMapper.toModel(expectedEventDTO);

        //when
        when(eventRepository.save(expectedSavedEvent)).thenReturn(expectedSavedEvent);

        // then
        EventDTO createdEventDTO = eventService.createEvent(expectedEventDTO);
        assertThat(createdEventDTO.getId(), is(equalTo(expectedEventDTO.getId())));
        assertThat(createdEventDTO.getClient(), is(equalTo(expectedEventDTO.getClient())));
        assertThat(createdEventDTO.getSchedule(), is(equalTo(expectedEventDTO.getSchedule())));
    }
}
