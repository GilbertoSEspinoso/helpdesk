package com.gbproductions.helpdesk.services;

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

import java.util.List;

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
        Tecnico tec2 = new Tecnico(null, "Marlon Rocha Sobrinho", "00123211256", "marlonsobrinho@email.com", "123");
        Tecnico tec3 = new Tecnico(null, "Richard Kleytson da Silva", "00040277721", "pitbull@gmail.com", "123");
        Tecnico tec4 = new Tecnico(null, "Vigoroso Sobrinho Babul", "43444455500", "vigorosogg@gmail.com", "123");
        Tecnico tec5 = new Tecnico(null, "Renan Gonçalvez Neves", "22200011199", "renantd2@gmail.com", "123");
        Tecnico tec6 = new Tecnico(null, "Rafael da Silva Rodrigues", "00000223119", "rafealboladao@gmail.com", "123");

        Cliente clie1 = new Cliente(null, "João Mendonça Gonçalves", "12134465415", "joaomendonca@hotmail.com", "123");
        Cliente clie2 = new Cliente(null, "Engie - Brasil", "00982828200", "contato@engie.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Desentupir o cano da pia", "O cano está quebrado, necessita ser consertado imediatamente", tec1, clie1);
        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Mensal-AVAC-ACJ21", "Manutenção mensal / sala do diretor da AVAC", tec2, clie2);
        Chamado c3 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Temperatura Elevada - Corredor vermelho", "Temperatura elevada no segundo andar da enfermaria", tec3, clie2);
        Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Mensal-PF-SPLIT101", "Manutenção mensal/ sala da PF área restrita", tec4, clie2);

        tecnicoRepository.saveAll(List.of(tec1,tec2,tec3,tec4,tec5,tec6));
        clienteRepository.saveAll(List.of(clie1,clie2));
        chamadoRepository.saveAll(List.of(c1,c2,c3,c4));
    }
}
