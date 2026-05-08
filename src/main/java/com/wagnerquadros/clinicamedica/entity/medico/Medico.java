package com.wagnerquadros.clinicamedica.entity.medico;

import com.wagnerquadros.clinicamedica.entity.endereco.Endereco;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import jakarta.persistence.*;
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
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(CadastroMedicoDto dto) {
        this. nome = dto.nome();
        this.email= dto.email();
        this.crm = dto.crm();
        this.especialidade =  dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
    }
}
