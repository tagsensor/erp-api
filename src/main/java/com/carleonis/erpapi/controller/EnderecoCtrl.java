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

import com.carleonis.erpapi.service.EnderecoServ;
import com.carleonis.erpapi.model.dto.ImpEndereco;
import com.carleonis.erpapi.model.dto.OutEndereco;
import com.carleonis.erpapi.model.dto.mapper.EnderecoMapper;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins="*")
public class EnderecoCtrl {

    @Autowired
    private EnderecoServ enderecoServ;

    @Autowired
    private EnderecoMapper enderecoMapper;
    
    @GetMapping("/pessoa/{id}")
    public List<OutEndereco> listarByPessoa(@PathVariable Long id) {
        return enderecoMapper.listEntityToDto(enderecoServ.buscaByPessoa(id));
    }

    @GetMapping
    public List<OutEndereco> listar() {
        return enderecoMapper.listEntityToDto(enderecoServ.listar());
    }

    @GetMapping("/{id}")
    public OutEndereco buscar(@PathVariable Long id) {
        return enderecoMapper.entityToDto(enderecoServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutEndereco adicionar(@RequestBody @Valid ImpEndereco impEndereco) {
        return enderecoMapper.entityToDto(enderecoServ.adicionar(enderecoMapper.DtoToEntity(impEndereco)));
    }

    @PutMapping
    public OutEndereco alterar(@RequestBody @Valid ImpEndereco impEndereco) {
        return enderecoMapper.entityToDto(enderecoServ.alterar(enderecoMapper.DtoToEntity(impEndereco)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        enderecoServ.deletar(id);
    }

}
