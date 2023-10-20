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

        //ADICIONANDO TECNICOS PARA TESTES
        Tecnico tec1 = new Tecnico(null, "Gilberto da Silva Espinoso", "235.590.410-31", "gilberto.espinoso@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Marlon Rocha Sobrinho", "081.273.280-49", "marlonsobrinho@email.com", "123");
        Tecnico tec3 = new Tecnico(null, "Richard Kleytson da Silva", "017.471.050-07", "pitbull@gmail.com", "123");
        Tecnico tec4 = new Tecnico(null, "Vigoroso Sobrinho Babul", "741.309.520-30", "vigorosogg@gmail.com", "123");
        Tecnico tec5 = new Tecnico(null, "Renan Gonçalvez Neves", "111.082.050-03", "renantd2@gmail.com", "123");
        Tecnico tec6 = new Tecnico(null, "Rafael da Silva Rodrigues", "756.164.100-12", "rafealboladao@gmail.com", "123");

        //ADICIONANDO CLIENTES PARA TESTES
        Cliente clie1 = new Cliente(null, "João Mendonça Gonçalves", "932.810.370-38", "joaomendonca@hotmail.com", "123");
        Cliente clie2 = new Cliente(null, "Engie - Brasil", "439.552.340-45", "contato@engie.com", "123");

        //ADICIONANDO CHAMADOS PARA TESTES
        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Desentupir o cano da pia", "O cano está quebrado, necessita ser consertado imediatamente", tec1, clie1);
        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Mensal-AVAC-ACJ21", "Manutenção mensal / sala do diretor da AVAC", tec2, clie2);
        Chamado c3 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Temperatura Elevada - Corredor vermelho", "Temperatura elevada no segundo andar da enfermaria", tec3, clie2);
        Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Mensal-PF-SPLIT101", "Manutenção mensal/ sala da PF área restrita", tec4, clie2);

        tecnicoRepository.saveAll(List.of(tec1,tec2,tec3,tec4,tec5,tec6));
        clienteRepository.saveAll(List.of(clie1,clie2));
        chamadoRepository.saveAll(List.of(c1,c2,c3,c4));
    }
}
