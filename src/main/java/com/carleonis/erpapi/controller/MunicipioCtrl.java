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

import com.carleonis.erpapi.service.MunicipioServ;
import com.carleonis.erpapi.model.dto.ImpMunicipio;
import com.carleonis.erpapi.model.dto.OutMunicipio;
import com.carleonis.erpapi.model.dto.mapper.MunicipioMapper;

@RestController
@RequestMapping("/municipio")
@CrossOrigin(origins="*")
public class MunicipioCtrl {

    @Autowired
    private MunicipioServ municipioServ;

    @Autowired
    private MunicipioMapper municipioMapper;
    
    @GetMapping("/uf/{id_uf}")
    public List<OutMunicipio> listaByUf(@PathVariable Long id_uf) {
        return municipioMapper.listEntityToDto(municipioServ.listaByUf(id_uf));
    }

    @GetMapping
    public List<OutMunicipio> listar() {
        return municipioMapper.listEntityToDto(municipioServ.listar());
    }

    @GetMapping("/{id}")
    public OutMunicipio buscar(@PathVariable Long id) {
        return municipioMapper.entityToDto(municipioServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutMunicipio adicionar(@RequestBody @Valid ImpMunicipio impMunicipio) {
        return municipioMapper.entityToDto(municipioServ.adicionar(municipioMapper.DtoToEntity(impMunicipio)));
    }

    @PutMapping
    public OutMunicipio alterar(@RequestBody @Valid ImpMunicipio impMunicipio) {
        return municipioMapper.entityToDto(municipioServ.alterar(municipioMapper.DtoToEntity(impMunicipio)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        municipioServ.deletar(id);
    }

}
