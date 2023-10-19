package com.gbproductions.helpdesk.service;

import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.dtos.TecnicoDTO;
import com.gbproductions.helpdesk.repositories.TecnicoRepository;
import com.gbproductions.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    //------RETORNO DE UM TECNICO DO BANCO
    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow((() -> new ObjectNotFoundException("Id `" + id + "` não encontrado")));
    }


    //------RETORNO DE TODOS OS TECNICOS DO BANCO
    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    //------ENDPOINT PARA CRIAR NOVO TECNICO
    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        Tecnico newObj = new Tecnico(objDTO);
        return  repository.save(newObj);
    }
}
