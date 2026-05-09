package com.wagnerquadros.clinicamedica.controller;


import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import com.wagnerquadros.clinicamedica.entity.medico.dto.AtualizacaoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.ListagemMedicoDto;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroMedicoDto dto){
        repository.save(new Medico(dto));
    }

    @GetMapping
    public Page<ListagemMedicoDto> listar (@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao)
                .map(ListagemMedicoDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoMedicoDto dto){
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarInformacoes(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
