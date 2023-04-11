package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Endereco;
import com.carleonis.erpapi.model.dto.ImpEndereco;
import com.carleonis.erpapi.model.dto.OutEndereco;

@Component
public class EnderecoMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutEndereco entityToDto(Endereco endereco) {
        return modelMaper.map(endereco, OutEndereco.class);
    }

    public List<OutEndereco> listEntityToDto(List<Endereco> enderecos) {
        return enderecos.stream()
        		.map(endereco -> entityToDto(endereco))
        		.collect(Collectors.toList());
    }

    public Endereco DtoToEntity(ImpEndereco impEndereco) {
        return modelMaper.map(impEndereco, Endereco.class);
    }

    public List<Endereco> listDtoToEntity(List<ImpEndereco> impEnderecos) {
        return impEnderecos.stream()
        		.map(impEndereco -> DtoToEntity(impEndereco))
        		.collect(Collectors.toList());
    }

}
