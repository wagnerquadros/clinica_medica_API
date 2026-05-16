package com.wagnerquadros.clinicamedica.entity.consulta.dto;

import com.wagnerquadros.clinicamedica.entity.consulta.Consulta;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDto (
        Long id,
        Long medicoId,
        Long pacienteId,
        LocalDateTime data
) {

    public DetalhamentoConsultaDto(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
