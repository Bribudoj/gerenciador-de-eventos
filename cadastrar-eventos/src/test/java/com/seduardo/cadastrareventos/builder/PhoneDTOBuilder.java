package com.seduardo.cadastrareventos.builder;

import com.seduardo.cadastrareventos.dto.request.PhoneDTO;
import com.seduardo.cadastrareventos.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
public class PhoneDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private PhoneType type = PhoneType.MOBILE;

    @Builder.Default
    private String number = "(41)99284-6348";

    public PhoneDTO toPhoneDTO(){
        return new PhoneDTO(id,
                type,
                number);
    }
}