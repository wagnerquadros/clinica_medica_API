package com.wagnerquadros.clinicamedica.entity.medico.dto;

import com.wagnerquadros.clinicamedica.controller.MedicoController;
import com.wagnerquadros.clinicamedica.entity.medico.Especialidade;
import com.wagnerquadros.clinicamedica.entity.medico.Medico;

public record ListagemMedicoDto(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public ListagemMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
