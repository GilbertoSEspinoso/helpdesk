package com.gbproductions.helpdesk.resources;

import com.gbproductions.helpdesk.domain.Chamado;
import com.gbproductions.helpdesk.domain.dtos.ChamadoDTO;
import com.gbproductions.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    private ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    //---> ENDPOINT PARA CRIAR NOVO CHAMADO - POST
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
        Chamado Obj = service.create(objDTO);
        //caminho da url/uri do novo Chamado criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //---> ENDPOINT PARA ATUALIZAR UM CHAMADO - PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

}
