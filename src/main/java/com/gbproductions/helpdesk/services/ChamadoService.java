package com.gbproductions.helpdesk.services;

import com.gbproductions.helpdesk.domain.Chamado;
import com.gbproductions.helpdesk.domain.Cliente;
import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.domain.enums.Prioridade;
import com.gbproductions.helpdesk.domain.enums.Status;
import com.gbproductions.helpdesk.dtos.ChamadoDTO;
import com.gbproductions.helpdesk.repositories.ChamadoRepository;
import com.gbproductions.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;


    //---> ENDPOINT PARA RETORNAR UM CHAMADO - GET
    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Esse Chamado não existe."));
    }

    //---> ENDPOINT PARA RETORNAR TODOS CHAMADOS - GET - all
    public List<Chamado> findAll() {
        return repository.findAll();
    }

    //---> ENDPOINT PARA CRIAR NOVO CLIENTE - POST
    public Chamado create(@Valid ChamadoDTO objDTO) {
        return repository.save(novoChamado(objDTO));
    }

    //---> ENDPOINT PARA ATUALIZAR UM CLIENTE - PUT
    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = novoChamado(objDTO);
        return repository.save(oldObj);
    }


    //... METODO PARA IDENTIFICAR SE TECNICO E CLIENTE EXISTEM
    //... ESSE METODO SERVE TANTO PARA CRIAR COMO PARA ATUALIZAR
    private Chamado novoChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());
        // CASO NÃO EXISTA, UMA EXCEPTION SERA LANCADA ↗

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if (obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }
        // VALIDACAO DE STATUS (SE ENCERRADO) POR DATA DE ENCERRAMENTO ↗

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }
}
