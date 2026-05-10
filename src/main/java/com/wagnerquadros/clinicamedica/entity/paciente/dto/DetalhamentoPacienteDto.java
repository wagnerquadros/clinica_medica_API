package com.wagnerquadros.clinicamedica.entity.paciente.dto;

import com.wagnerquadros.clinicamedica.entity.endereco.Endereco;
import com.wagnerquadros.clinicamedica.entity.paciente.Paciente;

public record DetalhamentoPacienteDto(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco
) {
    public DetalhamentoPacienteDto(Paciente paciente){
        this(paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEndereco());
    }
}
