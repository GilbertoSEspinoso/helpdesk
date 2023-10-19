package com.gbproductions.helpdesk.resources;

import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.dtos.TecnicoDTO;
import com.gbproductions.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    //RETORNO DE UM TECNICO DO BANCO
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    //RETORNO DE TODOS OS TECNICOS DO BANCO
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDTo = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTo);
    }
}
