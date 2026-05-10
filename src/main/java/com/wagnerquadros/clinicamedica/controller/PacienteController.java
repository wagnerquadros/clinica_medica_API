package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.medico.dto.DetalhamentoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.paciente.Paciente;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.AtualizacaoPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.CadastroPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.DetalhamentoPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.ListagemPacienteDto;
import com.wagnerquadros.clinicamedica.repository.PacienteRepository;
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
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadstrar(@RequestBody @Valid CadastroPacienteDto dto,
                                   UriComponentsBuilder uriBuilder) {

        var paciente = new Paciente(dto);
        repository.save(paciente);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoPacienteDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoPacienteDto dto) {
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DetalhamentoPacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoPacienteDto(paciente));
    }
}
