package com.carleonis.erpapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carleonis.erpapi.config.UtilSecurity;
import com.carleonis.erpapi.model.dto.ImpSisPerItens;
import com.carleonis.erpapi.model.dto.OutSisPerItens;
import com.carleonis.erpapi.model.dto.mapper.SisPerItensMapper;
import com.carleonis.erpapi.service.SisPerItensServ;

@RestController
@RequestMapping("/sisPerItens")
@CrossOrigin(origins="*")
public class SisPerItensCtrl {
	
	@Autowired
    private UtilSecurity utilSecurity;

    @Autowired
    private SisPerItensServ sisPerItensserv;

    @Autowired
    private SisPerItensMapper sisPerItensMapper;

    @GetMapping
    public List<OutSisPerItens> listar() {
        return sisPerItensMapper.listEntityToDto(sisPerItensserv.listar());
    }
    
    @GetMapping("/pagina/{idSub}")
    public OutSisPerItens buscarPagina(@PathVariable int idSub) {
    	Long idPerfil = Long.parseLong(utilSecurity.getPerfil());
        return sisPerItensMapper.entityToDto(sisPerItensserv.buscarPagina(idSub, idPerfil));
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
