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

import com.carleonis.erpapi.service.TipoFoneServ;
import com.carleonis.erpapi.model.dto.ImpTipoFone;
import com.carleonis.erpapi.model.dto.OutTipoFone;
import com.carleonis.erpapi.model.dto.mapper.TipoFoneMapper;

@RestController
@RequestMapping("/tipoFone")
@CrossOrigin(origins="*")
public class TipoFoneCtrl {

    @Autowired
    private TipoFoneServ tipoFoneServ;

    @Autowired
    private TipoFoneMapper tipoFoneMapper;

    @GetMapping
    public List<OutTipoFone> listar() {
        return tipoFoneMapper.listEntityToDto(tipoFoneServ.listar());
    }

    @GetMapping("/{id}")
    public OutTipoFone buscar(@PathVariable Long id) {
        return tipoFoneMapper.entityToDto(tipoFoneServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutTipoFone adicionar(@RequestBody @Valid ImpTipoFone impTipoFone) {
        return tipoFoneMapper.entityToDto(tipoFoneServ.adicionar(tipoFoneMapper.DtoToEntity(impTipoFone)));
    }

    @PutMapping
    public OutTipoFone alterar(@RequestBody @Valid ImpTipoFone impTipoFone) {
        return tipoFoneMapper.entityToDto(tipoFoneServ.alterar(tipoFoneMapper.DtoToEntity(impTipoFone)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        tipoFoneServ.deletar(id);
    }

}
