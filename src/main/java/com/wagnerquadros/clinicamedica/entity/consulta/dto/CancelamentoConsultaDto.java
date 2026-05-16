package com.wagnerquadros.clinicamedica.entity.consulta.dto;

import com.wagnerquadros.clinicamedica.entity.consulta.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDto (
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
