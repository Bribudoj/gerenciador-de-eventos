package com.seduardo.cadastrareventos.builder;

import com.seduardo.cadastrareventos.dto.request.ScheduleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Builder
public class ScheduleDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String date = "01-10-2021";

    @Builder.Default
    private String startTime = "19:00";

    @Builder.Default
    private String endTime = "20:00";

    public ScheduleDTO toScheduleDTO(){
        return new ScheduleDTO(id,
                date,
                startTime,
                endTime
                );
    }
}
