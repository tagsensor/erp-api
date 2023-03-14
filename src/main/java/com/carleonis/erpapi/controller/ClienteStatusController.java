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

import com.carleonis.erpapi.service.ClienteStatusService;
import com.carleonis.erpapi.model.dto.ClienteStDTOimput;
import com.carleonis.erpapi.model.dto.ClienteStDTO;
import com.carleonis.erpapi.model.dto.mapper.ClienteStatusMapper;

@RestController
@RequestMapping("/clienteStatus")
public class ClienteStatusController {

    @Autowired
    private ClienteStatusService clienteStatusService;

    @Autowired
    private ClienteStatusMapper clienteStatusMapper;

    @GetMapping
    public List<ClienteStDTO> listar() {
        return clienteStatusMapper.listEntityToDto(clienteStatusService.listar());
    }

    @GetMapping("/{id}")
    public ClienteStDTO buscar(@PathVariable Long id) {
        return clienteStatusMapper.entityToDto(clienteStatusService.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteStDTO adicionar(@RequestBody @Valid ClienteStDTOimput clienteStDTOimput) {
        return clienteStatusMapper.entityToDto(clienteStatusService.adicionar(clienteStatusMapper.DtoToEntity(clienteStDTOimput)));
    }

    @PutMapping
    public ClienteStDTO alterar(@RequestBody @Valid ClienteStDTOimput clienteStDTOimput) {
        return clienteStatusMapper.entityToDto(clienteStatusService.alterar(clienteStatusMapper.DtoToEntity(clienteStDTOimput)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteStatusService.deletar(id);
    }

}
