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

import com.carleonis.erpapi.service.SisSegurServ;
import com.carleonis.erpapi.model.dto.ImpSisSegur;
import com.carleonis.erpapi.model.dto.OutSisSegur;
import com.carleonis.erpapi.model.dto.mapper.SisSegurMapper;

@RestController
@RequestMapping("/sissegur")
public class SisSegurCtrl {

    @Autowired
    private SisSegurServ sissegurserv;

    @Autowired
    private SisSegurMapper sissegurMapper;

    @GetMapping
    public List<OutSisSegur> listar() {
        return sissegurMapper.listEntityToDto(sissegurserv.listar());
    }

    @GetMapping("/{id}")
    public OutSisSegur buscar(@PathVariable Long id) {
        return sissegurMapper.entityToDto(sissegurserv.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutSisSegur adicionar(@RequestBody @Valid ImpSisSegur impSisSegur) {
        return sissegurMapper.entityToDto(sissegurserv.adicionar(sissegurMapper.DtoToEntity(impSisSegur)));
    }

    @PutMapping
    public OutSisSegur alterar(@RequestBody @Valid ImpSisSegur impSisSegur) {
        return sissegurMapper.entityToDto(sissegurserv.alterar(sissegurMapper.DtoToEntity(impSisSegur)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sissegurserv.deletar(id);
    }

}
