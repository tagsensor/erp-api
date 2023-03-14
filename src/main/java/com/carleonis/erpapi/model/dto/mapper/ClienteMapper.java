package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Cliente;
import com.carleonis.erpapi.model.dto.ClienteDTOimput;
import com.carleonis.erpapi.model.dto.ClienteDTO;

@Component
public class ClienteMapper{

    @Autowired
    private ModelMapper modelMaper;

    public ClienteDTO entityToDto(Cliente cliente) {
        return modelMaper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> listEntityToDto(List<Cliente> clientes) {
        return clientes.stream()
        		.map(cliente -> entityToDto(cliente))
        		.collect(Collectors.toList());
    }

    public Cliente DtoToEntity(ClienteDTOimput clienteDTOimput) {
        return modelMaper.map(clienteDTOimput, Cliente.class);
    }

    public List<Cliente> listDtoToEntity(List<ClienteDTOimput> clienteDTOimputs) {
        return clienteDTOimputs.stream()
        		.map(clienteDTOimput -> DtoToEntity(clienteDTOimput))
        		.collect(Collectors.toList());
    }

}
