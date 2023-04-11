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

import com.carleonis.erpapi.service.TipoEndServ;
import com.carleonis.erpapi.model.dto.ImpTipoEnd;
import com.carleonis.erpapi.model.dto.OutTipoEnd;
import com.carleonis.erpapi.model.dto.mapper.TipoEndMapper;

@RestController
@RequestMapping("/tipoEnd")
@CrossOrigin(origins="*")
public class TipoEndCtrl {

    @Autowired
    private TipoEndServ tipoEndServ;

    @Autowired
    private TipoEndMapper tipoEndMapper;

    @GetMapping
    public List<OutTipoEnd> listar() {
        return tipoEndMapper.listEntityToDto(tipoEndServ.listar());
    }

    @GetMapping("/{id}")
    public OutTipoEnd buscar(@PathVariable Long id) {
        return tipoEndMapper.entityToDto(tipoEndServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutTipoEnd adicionar(@RequestBody @Valid ImpTipoEnd impTipoEnd) {
        return tipoEndMapper.entityToDto(tipoEndServ.adicionar(tipoEndMapper.DtoToEntity(impTipoEnd)));
    }

    @PutMapping
    public OutTipoEnd alterar(@RequestBody @Valid ImpTipoEnd impTipoEnd) {
        return tipoEndMapper.entityToDto(tipoEndServ.alterar(tipoEndMapper.DtoToEntity(impTipoEnd)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        tipoEndServ.deletar(id);
    }

}
