package com.wagnerquadros.clinicamedica.entity.medico;

import com.wagnerquadros.clinicamedica.entity.endereco.Endereco;
import com.wagnerquadros.clinicamedica.entity.medico.dto.AtualizacaoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(CadastroMedicoDto dto) {
        this.ativo = true;
        this. nome = dto.nome();
        this.email= dto.email();
        this.telefone = dto.telefone();
        this.crm = dto.crm();
        this.especialidade =  dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizarInformacoes(@Valid AtualizacaoMedicoDto dto) {
        if (dto.nome() != null)
            this.nome = dto.nome();

        if (dto.telefone() != null)
            this.telefone = dto.telefone();

        if (dto.endereco() != null)
            this.endereco.atualizarInformacoes(dto.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
