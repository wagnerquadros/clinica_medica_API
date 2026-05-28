package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.usuario.dto.AutenticacaoRequestDto;
import com.wagnerquadros.clinicamedica.infra.security.dto.TokenJwtDto;
import com.wagnerquadros.clinicamedica.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @Operation(summary = "Efetuar login")
    public ResponseEntity<TokenJwtDto> efeturLogin(@RequestBody @Valid AutenticacaoRequestDto dto) {
        return ResponseEntity.ok(autenticacaoService.efeturLogin(dto));
    }
}
