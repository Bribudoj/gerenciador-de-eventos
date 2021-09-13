package com.seduardo.cadastrareventos.mapper;

import com.seduardo.cadastrareventos.dto.request.ScheduleDTO;
import com.seduardo.cadastrareventos.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(target = "date",source = "date", dateFormat = "dd-MM-yyyy")
    Schedule toModel(ScheduleDTO scheduleDTO);
}
