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

import com.carleonis.erpapi.service.UfServ;
import com.carleonis.erpapi.model.dto.ImpUf;
import com.carleonis.erpapi.model.dto.OutUf;
import com.carleonis.erpapi.model.dto.mapper.UfMapper;

@RestController
@RequestMapping("/uf")
@CrossOrigin(origins="*")
public class UfCtrl {

    @Autowired
    private UfServ ufServ;

    @Autowired
    private UfMapper ufMapper;

    @GetMapping
    public List<OutUf> listar() {
        return ufMapper.listEntityToDto(ufServ.listar());
    }

    @GetMapping("/{id}")
    public OutUf buscar(@PathVariable Long id) {
        return ufMapper.entityToDto(ufServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutUf adicionar(@RequestBody @Valid ImpUf impUf) {
        return ufMapper.entityToDto(ufServ.adicionar(ufMapper.DtoToEntity(impUf)));
    }

    @PutMapping
    public OutUf alterar(@RequestBody @Valid ImpUf impUf) {
        return ufMapper.entityToDto(ufServ.alterar(ufMapper.DtoToEntity(impUf)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ufServ.deletar(id);
    }

}
