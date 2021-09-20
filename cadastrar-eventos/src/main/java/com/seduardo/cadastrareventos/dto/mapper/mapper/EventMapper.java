package com.seduardo.cadastrareventos.dto.mapper.mapper;

import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "schedule.date",source = "schedule.date", dateFormat = "dd-MM-yyyy")
    Event toModel(EventDTO eventDTO);

    @Mapping(target = "schedule.date",source = "schedule.date", dateFormat = "dd-MM-yyyy")
    EventDTO toDTO(Event event);
}
