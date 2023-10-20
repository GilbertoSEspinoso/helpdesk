package com.gbproductions.helpdesk.services;

import com.gbproductions.helpdesk.domain.Chamado;
import com.gbproductions.helpdesk.repositories.ChamadoRepository;
import com.gbproductions.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;

    //---> ENDPOINT PARA RETORNAR UM CHAMADO - GET
    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Esse Chamado não existe ou já foi finalizado."));
    }
}
