package com.gbproductions.helpdesk.service;

import com.gbproductions.helpdesk.domain.Chamado;
import com.gbproductions.helpdesk.domain.Cliente;
import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.domain.enums.Perfil;
import com.gbproductions.helpdesk.domain.enums.Prioridade;
import com.gbproductions.helpdesk.domain.enums.Status;
import com.gbproductions.helpdesk.repositories.ChamadoRepository;
import com.gbproductions.helpdesk.repositories.ClienteRepository;
import com.gbproductions.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBservice {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Gilberto da Silva Espinoso", "12346288718", "gilberto.espinoso@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente clie1 = new Cliente(null, "João Mendonça Gonçalves", "12134465415", "joaomendonca@hotmail.com", "123");
        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Desentupir o cano da pia", "O cano está quebrado, necessita ser consertado imediatamente", tec1, clie1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(clie1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
