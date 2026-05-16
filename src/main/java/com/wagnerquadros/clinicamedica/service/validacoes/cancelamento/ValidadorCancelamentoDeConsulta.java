package com.wagnerquadros.clinicamedica.service.validacoes.cancelamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.CancelamentoConsultaDto;

public interface ValidadorCancelamentoDeConsulta {
    void validar(CancelamentoConsultaDto dto);
}
