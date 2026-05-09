package com.wagnerquadros.clinicamedica.entity.paciente.dto;

import com.wagnerquadros.clinicamedica.entity.medico.dto.ListagemMedicoDto;
import com.wagnerquadros.clinicamedica.entity.paciente.Paciente;

public record ListagemPacienteDto(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone
) {
    public ListagemPacienteDto (Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }
}
