package com.gbproductions.helpdesk.services;

import com.gbproductions.helpdesk.domain.Cliente;
import com.gbproductions.helpdesk.domain.Pessoa;
import com.gbproductions.helpdesk.domain.dtos.ClienteDTO;
import com.gbproductions.helpdesk.repositories.ClienteRepository;
import com.gbproductions.helpdesk.repositories.PessoaRepository;
import com.gbproductions.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    //---> ENDPOINT PARA RETORNAR UM CLIENTE - GET
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow((() -> new ObjectNotFoundException("Id `" + id + "` não encontrado")));
    }

    //---> ENDPOINT PARA RETORNAR TODOS OS CLIENTES - GET
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    //---> ENDPOINT PARA CRIAR NOVO CLIENTE - POST
    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }

    //---> ENDPOINT PARA ATUALIZAR UM CLIENTE - PUT
    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        findById(id);
        validaPorCpfEEmail(objDTO);
        Cliente obj = new Cliente(objDTO);
        return repository.save(obj);
    }

    //---> ENDPOINT PARA DELETAR UM CLIENTE - DELETE
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("AÇÃO NEGADA! - Cliente possui ordem(s) de serviço(s) ATIVAS.");
        }
        repository.deleteById(id);
    }


    //...METODO USADO POR >> `CREATE` e `UPDATE` PARA VALIDAR CPF e EMAIL
    private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
