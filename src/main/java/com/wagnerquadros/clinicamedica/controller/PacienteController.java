package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.medico.dto.AtualizacaoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.ListagemMedicoDto;
import com.wagnerquadros.clinicamedica.entity.paciente.Paciente;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.AtualizacaoPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.CadastroPacienteDto;
import com.wagnerquadros.clinicamedica.entity.paciente.dto.ListagemPacienteDto;
import com.wagnerquadros.clinicamedica.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadstrar(@RequestBody @Valid CadastroPacienteDto dto) {
        repository.save(new Paciente(dto));
    }

    @GetMapping
    public Page<ListagemPacienteDto> listar (@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoPacienteDto dto){
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarInformacoes(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
