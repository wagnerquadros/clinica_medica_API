package com.wagnerquadros.clinicamedica.service;

import com.wagnerquadros.clinicamedica.entity.usuario.Usuario;
import com.wagnerquadros.clinicamedica.entity.usuario.dto.AutenticacaoRequestDto;
import com.wagnerquadros.clinicamedica.infra.security.TokenService;
import com.wagnerquadros.clinicamedica.infra.security.dto.TokenJwtDto;
import com.wagnerquadros.clinicamedica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public TokenJwtDto efeturLogin(AutenticacaoRequestDto dto){

        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authenticationManager.authenticate(token);
        var tokenAuthenticated = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return new TokenJwtDto(tokenAuthenticated);
    }
}
