package com.wagnerquadros.clinicamedica.entity.paciente;

import com.wagnerquadros.clinicamedica.entity.endereco.Endereco;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.AtualizacaoPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.CadastroPacienteDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(CadastroPacienteDto dto) {
        this.ativo = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizarInformacoes(@Valid AtualizacaoPacienteDto dto) {
        if(dto.nome() != null)
            this.nome = dto.nome();

        if(dto.telefone() != null)
            this.telefone = dto.telefone();

        if(dto.endereco() != null)
            this.endereco.atualizarInformacoes(dto.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
