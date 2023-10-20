package com.gbproductions.helpdesk.resources;

import com.gbproductions.helpdesk.domain.Chamado;
import com.gbproductions.helpdesk.dtos.ChamadoDTO;
import com.gbproductions.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
    @Autowired
    private ChamadoService service;


    //---> ENDPOINT PARA RETORNAR UM CHAMADO - GET
    @GetMapping(value = "/{id}")
    private ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    //---> ENDPOINT PARA RETORNAR TODOS CHAMADOS - GET - all
    @GetMapping
    private ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDTO);
    }
}
