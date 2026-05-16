package com.wagnerquadros.clinicamedica.service.validacoes.agendamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.infra.exception.ValidacaoException;
import com.wagnerquadros.clinicamedica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar (AgendamentoConsultaDto dto) {
        var medicoPossuiOutraCOnsultaNoMesmoHorario =
                consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dto.medicoId(), dto.data());
        if(medicoPossuiOutraCOnsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui consulta agendada nesse mesmo horário");

        }
    }

}
