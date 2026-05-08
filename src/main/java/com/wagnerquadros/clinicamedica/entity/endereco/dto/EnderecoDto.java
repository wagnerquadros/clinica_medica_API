package com.wagnerquadros.clinicamedica.entity.endereco.dto;

public record EnderecoDto(
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String complemento,
        String numero
) {
}
