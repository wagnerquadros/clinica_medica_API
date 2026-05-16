package com.wagnerquadros.clinicamedica.entity.consulta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wagnerquadros.clinicamedica.entity.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDto (

        Long medicoId,

        @NotNull
        Long pacienteId,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade
) {
}
