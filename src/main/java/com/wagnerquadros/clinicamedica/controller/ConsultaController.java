package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.CancelamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.DetalhamentoConsultaDto;
import com.wagnerquadros.clinicamedica.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    @Operation(summary = "Agendar consulta médica")
    public ResponseEntity<DetalhamentoConsultaDto> agendar(@RequestBody @Valid AgendamentoConsultaDto dto) {

        var detalhamentoConsultaDto = consultaService.agendar(dto);
        return ResponseEntity.ok(detalhamentoConsultaDto);
    }

    @DeleteMapping
    @Transactional
    @Operation(summary = "Cancelar consulta médica")
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancelamentoConsultaDto dto) {
        consultaService.cancelar(dto);
        return ResponseEntity.noContent().build();
    }
}
