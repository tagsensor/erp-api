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

import com.carleonis.erpapi.service.SisPerfilServ;
import com.carleonis.erpapi.model.dto.ImpSisPerfil;
import com.carleonis.erpapi.model.dto.OutSisPerfil;
import com.carleonis.erpapi.model.dto.mapper.SisPerfilMapper;

@RestController
@RequestMapping("/sisPerfil")
@CrossOrigin(origins="*")
public class SisPerfilCtrl {

    @Autowired
    private SisPerfilServ sisPerfilserv;

    @Autowired
    private SisPerfilMapper sisPerfilMapper;

    @GetMapping
    public List<OutSisPerfil> listar() {
        return sisPerfilMapper.listEntityToDto(sisPerfilserv.listar());
    }

    @GetMapping("/{id}")
    public OutSisPerfil buscar(@PathVariable Long id) {
        return sisPerfilMapper.entityToDto(sisPerfilserv.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutSisPerfil adicionar(@RequestBody @Valid ImpSisPerfil impSisPerfil) {
        return sisPerfilMapper.entityToDto(sisPerfilserv.adicionar(sisPerfilMapper.DtoToEntity(impSisPerfil)));
    }

    @PutMapping
    public OutSisPerfil alterar(@RequestBody @Valid ImpSisPerfil impSisPerfil) {
        return sisPerfilMapper.entityToDto(sisPerfilserv.alterar(sisPerfilMapper.DtoToEntity(impSisPerfil)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sisPerfilserv.deletar(id);
    }

}
