package com.wagnerquadros.clinicamedica.service.validacoes.agendamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{

    public void validar(AgendamentoConsultaDto dto){

        var dataConsulta = dto.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClienica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoDaClienica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClienica || depoisDoFechamentoDaClienica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica.");
        }
    }
}
