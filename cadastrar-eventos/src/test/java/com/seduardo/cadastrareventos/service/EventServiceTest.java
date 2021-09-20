package com.seduardo.cadastrareventos.service;

import com.seduardo.cadastrareventos.builder.EventDTOBuilder;
import com.seduardo.cadastrareventos.dto.mapper.mapper.EventMapper;
import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.exception.EventNotFoundException;
import com.seduardo.cadastrareventos.model.Event;
import com.seduardo.cadastrareventos.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    private static final long INVALID_EVENT_ID = 1L;

    @Mock
    private EventRepository eventRepository;

    private EventMapper eventMapper = EventMapper.INSTANCE;

    @InjectMocks
    private EventService eventService;

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

    @Test
    void whenFindAllIsCalledShouldReturnAllEvents() {
        //given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        Event expectedEvent = eventMapper.toModel(expectedEventDTO);

        //when
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(expectedEvent));

        //then
        List<EventDTO> foundEventListDTO = eventService.listAll();

        assertThat(foundEventListDTO, is(not(empty())));
        assertThat(foundEventListDTO.get(0), is(equalTo(expectedEventDTO)));

    }

    @Test
    void whenValidIdIsInformedThenReturnAEvent() throws EventNotFoundException {
        // given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        Event expectedEvent = eventMapper.toModel(expectedEventDTO);

        //when
        when(eventRepository.findById(expectedEventDTO.getId())).thenReturn(Optional.of(expectedEvent));

        //then
        EventDTO foundEventDTO = eventService.findById(expectedEventDTO.getId());

        assertThat(foundEventDTO, is(equalTo(expectedEventDTO)));
    }

    @Test
    void whenInvalidIdIsInformedThenThrowException(){
        // given
        EventDTO expectedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        when(eventRepository.findById(expectedEventDTO.getId())).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(EventNotFoundException.class, () -> eventService.findById(expectedEventDTO.getId()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAEventShouldBeDeleted() throws EventNotFoundException {
        // given
        EventDTO expectedDeletedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        Event expectedDeletedEvent = eventMapper.toModel(expectedDeletedEventDTO);

        //when
        when(eventRepository.findById(expectedDeletedEventDTO.getId())).thenReturn(Optional.of(expectedDeletedEvent));
        doNothing().when(eventRepository).deleteById(expectedDeletedEventDTO.getId());

        //then
        eventService.deleteById(expectedDeletedEventDTO.getId());

        verify(eventRepository, times(1)).findById(expectedDeletedEventDTO.getId());
        verify(eventRepository, times(1)).deleteById(expectedDeletedEventDTO.getId());
    }

    @Test
    void whenExclusionIsCalledWithInvalidIdThenThrowException(){
        // given
        EventDTO expectedDeletedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        when(eventRepository.findById(expectedDeletedEventDTO.getId())).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(EventNotFoundException.class, () -> eventService.findById(expectedDeletedEventDTO.getId()));
    }

    @Test
    void whenUpdateIsCalledWithValidIdAEventShouldBeUpdated(){
        // given
        EventDTO expectedUpdatedEventDTO = EventDTOBuilder.builder().build().toEventDTO();
        Event expectedUpdatedEvent = eventMapper.toModel(expectedUpdatedEventDTO);

        //when
        when(eventRepository.save(expectedUpdatedEvent)).thenReturn(expectedUpdatedEvent);

        // then
        EventDTO updatedEventDTO = eventService.createEvent(expectedUpdatedEventDTO);

        assertThat(updatedEventDTO.getId(), is(equalTo(expectedUpdatedEventDTO.getId())));
        assertThat(updatedEventDTO.getClient(), is(equalTo(expectedUpdatedEventDTO.getClient())));
        assertThat(updatedEventDTO.getSchedule(), is(equalTo(expectedUpdatedEventDTO.getSchedule())));
    }

    @Test
    void whenUpdateIsCalledWithInvalidIdThrowException(){
        // given
        EventDTO expectedUpdatedEventDTO = EventDTOBuilder.builder().build().toEventDTO();

        //when
        when(eventRepository.findById(expectedUpdatedEventDTO.getId())).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(EventNotFoundException.class, () -> eventService.findById(expectedUpdatedEventDTO.getId()));
    }


}
