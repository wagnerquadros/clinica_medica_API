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

    public void atualizarInformacoes(EnderecoDto dto) {
        if (dto.logradouro() != null)
            this.logradouro = dto.logradouro();

        if (dto.bairro() != null)
            this.bairro = dto.bairro();

        if (dto.cep() != null)
            this.cep = dto.cep();

        if (dto.cidade() != null)
            this.cidade = dto.cidade();

        if (dto.uf() != null)
            this.uf = dto.uf();

        if (dto.numero() != null)
            this.numero = dto.numero();

        if (dto.complemento() != null)
            this.complemento = dto.complemento();
    }
}