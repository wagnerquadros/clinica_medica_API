package com.wagnerquadros.clinicamedica.entity.endereco;

import com.wagnerquadros.clinicamedica.entity.endereco.dto.EnderecoDto;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDto dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
    }
}