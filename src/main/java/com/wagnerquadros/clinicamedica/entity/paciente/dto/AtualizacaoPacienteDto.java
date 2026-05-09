package com.wagnerquadros.clinicamedica.entity.paciente.dto;

import com.wagnerquadros.clinicamedica.entity.endereco.dto.EnderecoDto;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoPacienteDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco
) {
}
