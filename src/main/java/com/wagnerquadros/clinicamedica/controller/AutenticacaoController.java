package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.usuario.Usuario;
import com.wagnerquadros.clinicamedica.entity.usuario.dto.AutenticacaoRequestDto;
import com.wagnerquadros.clinicamedica.infra.security.TokenService;
import com.wagnerquadros.clinicamedica.infra.security.dto.TokenJwtDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity efeturLogin (@RequestBody @Valid AutenticacaoRequestDto dto){

        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authenticationManager.authenticate(token);
        var tokenAuthenticated = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtDto(tokenAuthenticated));
    }
}
