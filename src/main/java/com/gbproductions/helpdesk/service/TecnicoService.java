package com.gbproductions.helpdesk.service;

import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.repositories.TecnicoRepository;
import com.gbproductions.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow((() -> new ObjectNotFoundException("Id `" + id + "` não encontrado")));
    }
}
