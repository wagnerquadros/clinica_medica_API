package com.wagnerquadros.clinicamedica.service.validacoes.agendamento;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.infra.exception.ValidacaoException;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(AgendamentoConsultaDto dto) {

        if (dto.medicoId() == null) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dto.medicoId());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
