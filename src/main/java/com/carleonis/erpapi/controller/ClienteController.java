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

import com.carleonis.erpapi.service.ClienteService;
import com.carleonis.erpapi.model.dto.ClienteDTOimput;
import com.carleonis.erpapi.model.dto.ClienteDTO;
import com.carleonis.erpapi.model.dto.mapper.ClienteMapper;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins="*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteMapper.listEntityToDto(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ClienteDTO buscar(@PathVariable Long id) {
        return clienteMapper.entityToDto(clienteService.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteDTO adicionar(@RequestBody @Valid ClienteDTOimput clienteDTOimput) {
        return clienteMapper.entityToDto(clienteService.adicionar(clienteMapper.DtoToEntity(clienteDTOimput)));
    }

    @PutMapping
    public ClienteDTO alterar(@RequestBody @Valid ClienteDTOimput clienteDTOimput) {
        return clienteMapper.entityToDto(clienteService.alterar(clienteMapper.DtoToEntity(clienteDTOimput)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }

}
