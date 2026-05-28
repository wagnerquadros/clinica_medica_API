package com.wagnerquadros.clinicamedica.controller;


import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import com.wagnerquadros.clinicamedica.entity.medico.dto.AtualizacaoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.DetalhamentoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.ListagemMedicoDto;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import com.wagnerquadros.clinicamedica.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Operation(summary = "Cadastrar médico")
    public ResponseEntity<DetalhamentoMedicoDto> cadastrar(@RequestBody @Valid CadastroMedicoDto dto,
                                    UriComponentsBuilder uriBuilder) {

        var medicoDto = medicoService.cadastrar(dto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoDto.id()).toUri();

        return ResponseEntity.created(uri).body(medicoDto);
    }

    @GetMapping
    @Operation(summary = "Listar médicos")
    public ResponseEntity<Page<ListagemMedicoDto>> listar(@PageableDefault(size = 10, sort = {"nome"})
                                                              Pageable paginacao) {
        return ResponseEntity.ok(medicoService.listar(paginacao));
    }

    @PutMapping
    @Operation(summary = "Atualizar médico")
    public ResponseEntity<DetalhamentoMedicoDto> atualizar(@RequestBody @Valid AtualizacaoMedicoDto dto) {
        return ResponseEntity.ok(medicoService.atualizar(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir logicamente um médico")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar dados de um médico")
    public ResponseEntity<DetalhamentoMedicoDto> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.detalhar(id));
    }
}
