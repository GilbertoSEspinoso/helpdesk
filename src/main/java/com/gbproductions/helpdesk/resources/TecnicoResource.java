package com.gbproductions.helpdesk.resources;

import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.dtos.TecnicoDTO;
import com.gbproductions.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    //------RETORNO DE UM TECNICO DO BANCO
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    //------RETORNO DE TODOS OS TECNICOS DO BANCO
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    //------ENDPOINT PARA CRIAR NOVO TECNICO
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO objDTO){
        Tecnico newObj = service.create(objDTO);

        //caminho da url/uri do novo tecnico criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
