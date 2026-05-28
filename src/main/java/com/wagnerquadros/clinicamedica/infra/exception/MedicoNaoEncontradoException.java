package com.wagnerquadros.clinicamedica.infra.exception;

public class MedicoNaoEncontradoException extends RuntimeException {
    public MedicoNaoEncontradoException(Long id) {
        super("Médico não encontrado com o id: " + id);
    }
}
