package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.ClienteStatus;
import com.carleonis.erpapi.model.dto.ClienteStDTOimput;
import com.carleonis.erpapi.model.dto.ClienteStDTO;

@Component
public class ClienteStatusMapper{

    @Autowired
    private ModelMapper modelMaper;

    public ClienteStDTO entityToDto(ClienteStatus clienteStatus) {
        return modelMaper.map(clienteStatus, ClienteStDTO.class);
    }

    public List<ClienteStDTO> listEntityToDto(List<ClienteStatus> clienteStatuss) {
        return clienteStatuss.stream()
        		.map(clienteStatus -> entityToDto(clienteStatus))
        		.collect(Collectors.toList());
    }

    public ClienteStatus DtoToEntity(ClienteStDTOimput clienteStDTOimput) {
        return modelMaper.map(clienteStDTOimput, ClienteStatus.class);
    }

    public List<ClienteStatus> listDtoToEntity(List<ClienteStDTOimput> clienteStDTOimputs) {
        return clienteStDTOimputs.stream()
        		.map(clienteStDTOimput -> DtoToEntity(clienteStDTOimput))
        		.collect(Collectors.toList());
    }

}
