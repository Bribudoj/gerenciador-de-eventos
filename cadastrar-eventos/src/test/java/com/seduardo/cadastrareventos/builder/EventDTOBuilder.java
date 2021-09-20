package com.seduardo.cadastrareventos.builder;

import com.seduardo.cadastrareventos.dto.request.ClientDTO;
import com.seduardo.cadastrareventos.dto.request.EventDTO;
import com.seduardo.cadastrareventos.dto.request.ScheduleDTO;
import lombok.Builder;

@Builder
public class EventDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private ClientDTO client = ClientDTOBuilder.builder().build().toClientDTO();

    @Builder.Default
    private ScheduleDTO schedule = ScheduleDTOBuilder.builder().build().toScheduleDTO();

    public EventDTO toEventDTO() {
        return new EventDTO(id,
                client,
                schedule
        );
    }
}
