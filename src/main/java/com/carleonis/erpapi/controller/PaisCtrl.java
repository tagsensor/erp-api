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

import com.carleonis.erpapi.service.PaisServ;
import com.carleonis.erpapi.model.dto.ImpPais;
import com.carleonis.erpapi.model.dto.OutPais;
import com.carleonis.erpapi.model.dto.mapper.PaisMapper;

@RestController
@RequestMapping("/pais")
@CrossOrigin(origins="*")
public class PaisCtrl {

    @Autowired
    private PaisServ paisServ;

    @Autowired
    private PaisMapper paisMapper;

    @GetMapping
    public List<OutPais> listar() {
        return paisMapper.listEntityToDto(paisServ.listar());
    }

    @GetMapping("/{id}")
    public OutPais buscar(@PathVariable Long id) {
        return paisMapper.entityToDto(paisServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutPais adicionar(@RequestBody @Valid ImpPais impPais) {
        return paisMapper.entityToDto(paisServ.adicionar(paisMapper.DtoToEntity(impPais)));
    }

    @PutMapping
    public OutPais alterar(@RequestBody @Valid ImpPais impPais) {
        return paisMapper.entityToDto(paisServ.alterar(paisMapper.DtoToEntity(impPais)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        paisServ.deletar(id);
    }

}
