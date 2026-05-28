package com.wagnerquadros.clinicamedica.service;

import com.wagnerquadros.clinicamedica.entity.medico.Medico;
import com.wagnerquadros.clinicamedica.entity.medico.dto.AtualizacaoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.CadastroMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.DetalhamentoMedicoDto;
import com.wagnerquadros.clinicamedica.entity.medico.dto.ListagemMedicoDto;
import com.wagnerquadros.clinicamedica.infra.exception.MedicoNaoEncontradoException;
import com.wagnerquadros.clinicamedica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public DetalhamentoMedicoDto cadastrar(CadastroMedicoDto dto){

        var medico = new Medico(dto);
        medicoRepository.save(medico);

        return new DetalhamentoMedicoDto(medico);
    }

    public Page<ListagemMedicoDto> listar(Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDto::new);
    }

    @Transactional
    public DetalhamentoMedicoDto atualizar(AtualizacaoMedicoDto dto) {
        var medico = medicoRepository.getReferenceById(dto.id());
        medico.atualizarInformacoes(dto);

        return new DetalhamentoMedicoDto(medico);
    }

    @Transactional
    public void excluir(Long id) {

        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNaoEncontradoException(id));

        medico.excluir();
    }

    @Transactional
    public DetalhamentoMedicoDto detalhar(Long id) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNaoEncontradoException(id));
        return new DetalhamentoMedicoDto(medico);
    }

}