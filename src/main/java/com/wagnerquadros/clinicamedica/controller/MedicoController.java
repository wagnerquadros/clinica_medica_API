package com.wagnerquadros.clinicamedica.controller;


import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody CadastroMedicoDto dto){
        repository.save(new Medico(dto));
    }
}
