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

import com.carleonis.erpapi.service.MenuItensServ;
import com.carleonis.erpapi.model.dto.ImpMenuItens;
import com.carleonis.erpapi.model.dto.OutMenuItens;
import com.carleonis.erpapi.model.dto.mapper.MenuItensMapper;

@RestController
@RequestMapping("/menuItens")
@CrossOrigin(origins="*")
public class MenuItensCtrl {

    @Autowired
    private MenuItensServ menuItensServ;

    @Autowired
    private MenuItensMapper menuItensMapper;

    @GetMapping
    public List<OutMenuItens> listar() {
        return menuItensMapper.listEntityToDto(menuItensServ.listar());
    }

    @GetMapping("/{id}")
    public OutMenuItens buscar(@PathVariable Long id) {
        return menuItensMapper.entityToDto(menuItensServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutMenuItens adicionar(@RequestBody @Valid ImpMenuItens impMenuitens) {
        return menuItensMapper.entityToDto(menuItensServ.adicionar(menuItensMapper.DtoToEntity(impMenuitens)));
    }

    @PutMapping
    public OutMenuItens alterar(@RequestBody @Valid ImpMenuItens impMenuitens) {
        return menuItensMapper.entityToDto(menuItensServ.alterar(menuItensMapper.DtoToEntity(impMenuitens)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        menuItensServ.deletar(id);
    }

}
