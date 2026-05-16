package com.wagnerquadros.clinicamedica.service.validacoes.agendamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.infra.exception.ValidacaoException;
import com.wagnerquadros.clinicamedica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar (AgendamentoConsultaDto dto) {

        var primeiroHorario = dto.data().withHour(7);
        var ultimoHorario = dto.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia =
                consultaRepository.existsByPacienteIdAndDataBetween(
                        dto.pacienteId(),
                        primeiroHorario,
                        ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente possui outra consulta.");
        }
    }
}
