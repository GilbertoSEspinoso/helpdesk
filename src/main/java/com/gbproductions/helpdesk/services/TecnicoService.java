package com.gbproductions.helpdesk.services;

import com.gbproductions.helpdesk.domain.Pessoa;
import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.dtos.TecnicoDTO;
import com.gbproductions.helpdesk.repositories.PessoaRepository;
import com.gbproductions.helpdesk.repositories.TecnicoRepository;
import com.gbproductions.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;


    //---> ENDPOINT PARA RETORNAR UM TECNICO - GET
    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow((() -> new ObjectNotFoundException("Id `" + id + "` não encontrado")));
    }

    //---> ENDPOINT PARA RETORNAR TODOS OS TECNICOS - GET
    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    //---> ENDPOINT PARA CRIAR NOVO TECNICO - POST
    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return  repository.save(newObj);
    }


    //...METODO USADO POR >> `CREATE` PARA VALIDAR CPF e EMAIL
    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

        //VALIDACAO PARA CPF
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Este cpf encontra-se cadastrado no sistema.");
        }

        //VALIDACAO PARA EMAIL
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Este email encontra-se cadastrado no sistema.");
        }
    }
}
