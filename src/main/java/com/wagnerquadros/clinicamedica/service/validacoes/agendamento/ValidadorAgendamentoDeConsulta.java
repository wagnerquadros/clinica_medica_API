package com.wagnerquadros.clinicamedica.service.validacoes.agendamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta {

    void validar(AgendamentoConsultaDto dto);
}
