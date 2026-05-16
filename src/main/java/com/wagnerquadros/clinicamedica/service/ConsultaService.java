package com.wagnerquadros.clinicamedica.service;

import com.wagnerquadros.clinicamedica.entity.consulta.Consulta;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.CancelamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.DetalhamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import com.wagnerquadros.clinicamedica.infra.exception.ValidacaoException;
import com.wagnerquadros.clinicamedica.repository.ConsultaRepository;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import com.wagnerquadros.clinicamedica.repository.PacienteRepository;
import com.wagnerquadros.clinicamedica.service.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import com.wagnerquadros.clinicamedica.service.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;


    public DetalhamentoConsultaDto agendar(AgendamentoConsultaDto dto) {

        if(!pacienteRepository.existsById(dto.pacienteId())) {
            throw new ValidacaoException("Id do paciente informado não localizado.");
        }

        if(dto.medicoId() != null  && !medicoRepository.existsById(dto.medicoId())) {
            throw new ValidacaoException("Id do médico informado não localizado.");
        }

        validadores.forEach(validador -> validador.validar(dto));

        var paciente = pacienteRepository.getReferenceById(dto.pacienteId());
        var medico = escolherMedico(dto);

        if(medico == null) {
            throw new ValidacaoException("Não existe médido disponível nessa data.");
        }

        var consulta = new Consulta(null, medico, paciente, dto.data(), null);

        consultaRepository.save(consulta);

        return new DetalhamentoConsultaDto(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDto dto) {
        if(dto.medicoId() != null) {
            return medicoRepository.getReferenceById(dto.medicoId());
        }

        if(dto.especialidade() == null){
            throw new ValidacaoException("Especialidade obrigatório quando médico não escolhido.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dto.especialidade(), dto.data());
    }

    public void cancelar(CancelamentoConsultaDto dto) {
        if (!consultaRepository.existsById(dto.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dto));

        var consulta = consultaRepository.getReferenceById(dto.idConsulta());
        consulta.cancelar(dto.motivo());
    }
}
