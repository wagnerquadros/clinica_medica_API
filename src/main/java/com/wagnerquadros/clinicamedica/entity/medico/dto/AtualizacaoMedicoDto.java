package com.wagnerquadros.clinicamedica.entity.medico.dto;

import com.wagnerquadros.clinicamedica.entity.endereco.dto.EnderecoDto;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoMedicoDto(

        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco
) {
}
