package com.seduardo.cadastrareventos.controller;

import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.exception.EventNotFoundException;
import com.seduardo.cadastrareventos.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO createEvent(@RequestBody @Valid EventDTO eventDTO){
        return eventService.createEvent(eventDTO);
    }

    @GetMapping
    public List<EventDTO> listAll(){return eventService.listAll();}

    @GetMapping({"/{id}"})
    public EventDTO findById(@PathVariable Long id) throws EventNotFoundException {
        return eventService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws EventNotFoundException {
        eventService.deleteById(id);
    }

    @PutMapping("/{id}")
    public EventDTO updateById(@PathVariable Long id, @RequestBody @Valid EventDTO eventDTO) throws EventNotFoundException {
        return eventService.updateById(id, eventDTO);
    }
}
