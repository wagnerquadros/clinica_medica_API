package com.wagnerquadros.clinicamedica.entity.medico.dto;

import com.wagnerquadros.clinicamedica.entity.endereco.Endereco;
import com.wagnerquadros.clinicamedica.entity.medico.Especialidade;
import com.wagnerquadros.clinicamedica.entity.medico.Medico;

public record DetalhamentoMedicoDto(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
        ) {
    public DetalhamentoMedicoDto(Medico medico){
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
