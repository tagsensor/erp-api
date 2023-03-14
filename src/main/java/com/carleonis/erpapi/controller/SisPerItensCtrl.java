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

import com.carleonis.erpapi.service.SisPerItensServ;
import com.carleonis.erpapi.model.dto.ImpSisPerItens;
import com.carleonis.erpapi.model.dto.OutSisPerItens;
import com.carleonis.erpapi.model.dto.mapper.SisPerItensMapper;

@RestController
@RequestMapping("/sisPerItens")
public class SisPerItensCtrl {

    @Autowired
    private SisPerItensServ sisPerItensserv;

    @Autowired
    private SisPerItensMapper sisPerItensMapper;

    @GetMapping
    public List<OutSisPerItens> listar() {
        return sisPerItensMapper.listEntityToDto(sisPerItensserv.listar());
    }

    @GetMapping("/{id}")
    public OutSisPerItens buscar(@PathVariable Long id) {
        return sisPerItensMapper.entityToDto(sisPerItensserv.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutSisPerItens adicionar(@RequestBody @Valid ImpSisPerItens impSisPeritens) {
        return sisPerItensMapper.entityToDto(sisPerItensserv.adicionar(sisPerItensMapper.DtoToEntity(impSisPeritens)));
    }

    @PutMapping
    public OutSisPerItens alterar(@RequestBody @Valid ImpSisPerItens impSisPeritens) {
        return sisPerItensMapper.entityToDto(sisPerItensserv.alterar(sisPerItensMapper.DtoToEntity(impSisPeritens)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sisPerItensserv.deletar(id);
    }

}
