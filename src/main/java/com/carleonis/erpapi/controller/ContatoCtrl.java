package com.carleonis.erpapi.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.carleonis.erpapi.service.ContatoServ;
import com.carleonis.erpapi.model.dto.ImpContato;
import com.carleonis.erpapi.model.dto.OutContato;
import com.carleonis.erpapi.model.dto.OutEndereco;
import com.carleonis.erpapi.model.dto.mapper.ContatoMapper;

@RestController
@RequestMapping("/contato")
@CrossOrigin(origins="*")
public class ContatoCtrl {

    @Autowired
    private ContatoServ contatoServ;

    @Autowired
    private ContatoMapper contatoMapper;
    
    @GetMapping("/pessoa/{id}")
    public List<OutContato> listarByPessoa(@PathVariable Long id) {
        return contatoMapper.listEntityToDto(contatoServ.buscaByPessoa(id));
    }

    @GetMapping
    public List<OutContato> listar() {
        return contatoMapper.listEntityToDto(contatoServ.listar());
    }

    @GetMapping("/{id}")
    public OutContato buscar(@PathVariable Long id) {
        return contatoMapper.entityToDto(contatoServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutContato adicionar(@RequestBody @Valid ImpContato impContato) {
        return contatoMapper.entityToDto(contatoServ.adicionar(contatoMapper.DtoToEntity(impContato)));
    }

    @PutMapping
    public OutContato alterar(@RequestBody @Valid ImpContato impContato) {
        return contatoMapper.entityToDto(contatoServ.alterar(contatoMapper.DtoToEntity(impContato)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        contatoServ.deletar(id);
    }

}
