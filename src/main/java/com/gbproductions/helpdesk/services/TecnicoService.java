package com.gbproductions.helpdesk.services;

import com.gbproductions.helpdesk.domain.Pessoa;
import com.gbproductions.helpdesk.domain.Tecnico;
import com.gbproductions.helpdesk.dtos.TecnicoDTO;
import com.gbproductions.helpdesk.repositories.PessoaRepository;
import com.gbproductions.helpdesk.repositories.TecnicoRepository;
import com.gbproductions.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    //---> ENDPOINT PARA RETORNAR UM TECNICO - GET
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow((() -> new ObjectNotFoundException("Id `" + id + "` não encontrado")));
    }

    //---> ENDPOINT PARA RETORNAR TODOS OS TECNICOS - GET
    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    //---> ENDPOINT PARA CRIAR NOVO TECNICO - POST
    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }

    //---> ENDPOINT PARA ATUALIZAR UM TECNICO - PUT
    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        objDTO.setId(id);
        findById(id);
        validaPorCpfEEmail(objDTO);
        Tecnico obj = new Tecnico(objDTO);
        return repository.save(obj);
    }

    //---> ENDPOINT PARA DELETAR UM TECNICO - DELETE
    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("AÇÃO NEGADA! - Técnico possui ordem(s) de serviço(s) ATIVAS.");
        }
        repository.deleteById(id);
    }


    //...METODO USADO POR >> `CREATE` e `UPDATE` PARA VALIDAR CPF e EMAIL
    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        //VALIDACAO PARA CPF
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Este cpf encontra-se cadastrado no sistema.");
        }
        //VALIDACAO PARA EMAIL
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Este email encontra-se cadastrado no sistema.");
        }
    }
}
