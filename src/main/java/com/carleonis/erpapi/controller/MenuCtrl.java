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

import com.carleonis.erpapi.service.MenuServ;
import com.carleonis.erpapi.model.dto.ImpMenu;
import com.carleonis.erpapi.model.dto.OutMenu;
import com.carleonis.erpapi.model.dto.mapper.MenuMapper;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins="*")
public class MenuCtrl {

    @Autowired
    private MenuServ menuServ;

    @Autowired
    private MenuMapper menuMapper;

    @GetMapping
    public List<OutMenu> listar() {
        return menuMapper.listEntityToDto(menuServ.listar());
    }

    @GetMapping("/{id}")
    public OutMenu buscar(@PathVariable Long id) {
        return menuMapper.entityToDto(menuServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutMenu adicionar(@RequestBody @Valid ImpMenu impMenu) {
        return menuMapper.entityToDto(menuServ.adicionar(menuMapper.DtoToEntity(impMenu)));
    }

    @PutMapping
    public OutMenu alterar(@RequestBody @Valid ImpMenu impMenu) {
        return menuMapper.entityToDto(menuServ.alterar(menuMapper.DtoToEntity(impMenu)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        menuServ.deletar(id);
    }

}
