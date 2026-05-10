package com.wagnerquadros.clinicamedica.controller;


import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import com.wagnerquadros.clinicamedica.entity.medico.dto.AtualizacaoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.DetalhamentoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.ListagemMedicoDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.DetalhamentoPacienteDto;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroMedicoDto dto,
                                    UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dto);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedicoDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDto>> listar (@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoMedicoDto dto){
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarInformacoes(dto);

        return  ResponseEntity.ok(new DetalhamentoMedicoDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoMedicoDto(medico));
    }
}
