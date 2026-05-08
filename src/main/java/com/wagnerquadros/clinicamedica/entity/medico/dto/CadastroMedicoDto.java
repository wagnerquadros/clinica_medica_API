package com.wagnerquadros.clinicamedica.entity.medico.dto;

import com.wagnerquadros.clinicamedica.entity.endereco.dto.EnderecoDto;
import com.wagnerquadros.clinicamedica.entity.medico.Especialidade;

public record CadastroMedicoDto(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        EnderecoDto endereco
) {
}
