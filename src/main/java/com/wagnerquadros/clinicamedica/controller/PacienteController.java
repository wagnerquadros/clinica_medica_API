package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.medico.dto.DetalhamentoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.paciente.Paciente;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.AtualizacaoPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.CadastroPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.DetalhamentoPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.ListagemPacienteDto;
import com.wagnerquadros.clinicamedica.repository.PacienteRepository;
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
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar paciente")
    public ResponseEntity<DetalhamentoPacienteDto> cadstrar(@RequestBody @Valid CadastroPacienteDto dto,
                                   UriComponentsBuilder uriBuilder) {

        var paciente = new Paciente(dto);
        repository.save(paciente);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoPacienteDto(paciente));
    }

    @GetMapping
    @Operation(summary = "Listar pacientes")
    public ResponseEntity<Page<ListagemPacienteDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar paciente")
    public ResponseEntity<DetalhamentoPacienteDto> atualizar(@RequestBody @Valid AtualizacaoPacienteDto dto) {
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DetalhamentoPacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir logicamente paciente")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar dados de um paciente")
    public ResponseEntity<DetalhamentoPacienteDto> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoPacienteDto(paciente));
    }
}
