package com.seduardo.cadastrareventos.builder;

import com.seduardo.cadastrareventos.dto.request.ClientDTO;
import com.seduardo.cadastrareventos.dto.request.PhoneDTO;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public class ClientDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String cpf = "048.879.799-32";

    @Builder.Default
    private String email = "somemail@mail.com";

    @Builder.Default
    private String firstName = "Eduardo";

    @Builder.Default
    private String lastName = "Santana";

    @Builder.Default
    private List<PhoneDTO> phones = Collections.singletonList(PhoneDTOBuilder.builder().build().toPhoneDTO());

    public ClientDTO toClientDTO() {
        return new ClientDTO(
                id,
                cpf,
                email,
                firstName,
                lastName,
                phones
        );
    }
}
