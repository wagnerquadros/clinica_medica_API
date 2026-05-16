package com.wagnerquadros.clinicamedica.service.validacoes.agendamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.infra.exception.ValidacaoException;
import com.wagnerquadros.clinicamedica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar (AgendamentoConsultaDto dto){
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dto.pacienteId());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciene excluído");
        }
    }

}
