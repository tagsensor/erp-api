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

import com.carleonis.erpapi.service.BairroServ;
import com.carleonis.erpapi.model.dto.ImpBairro;
import com.carleonis.erpapi.model.dto.OutBairro;
import com.carleonis.erpapi.model.dto.mapper.BairroMapper;

@RestController
@RequestMapping("/bairro")
@CrossOrigin(origins="*")
public class BairroCtrl {

    @Autowired
    private BairroServ bairroServ;

    @Autowired
    private BairroMapper bairroMapper;

    @GetMapping("/municipio/{id_mun}")
    public List<OutBairro> listaByMunicipio(@PathVariable Long id_mun) {
        return bairroMapper.listEntityToDto(bairroServ.listaByMunicipio(id_mun));
    }
    
    @GetMapping
    public List<OutBairro> listar() {
        return bairroMapper.listEntityToDto(bairroServ.listar());
    }

    @GetMapping("/{id}")
    public OutBairro buscar(@PathVariable Long id) {
        return bairroMapper.entityToDto(bairroServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutBairro adicionar(@RequestBody @Valid ImpBairro impBairro) {
        return bairroMapper.entityToDto(bairroServ.adicionar(bairroMapper.DtoToEntity(impBairro)));
    }

    @PutMapping
    public OutBairro alterar(@RequestBody @Valid ImpBairro impBairro) {
        return bairroMapper.entityToDto(bairroServ.alterar(bairroMapper.DtoToEntity(impBairro)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        bairroServ.deletar(id);
    }

}
