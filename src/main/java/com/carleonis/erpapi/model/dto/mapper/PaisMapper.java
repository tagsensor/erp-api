package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Pais;
import com.carleonis.erpapi.model.dto.ImpPais;
import com.carleonis.erpapi.model.dto.OutPais;

@Component
public class PaisMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutPais entityToDto(Pais pais) {
        return modelMaper.map(pais, OutPais.class);
    }

    public List<OutPais> listEntityToDto(List<Pais> paiss) {
        return paiss.stream()
        		.map(pais -> entityToDto(pais))
        		.collect(Collectors.toList());
    }

    public Pais DtoToEntity(ImpPais impPais) {
        return modelMaper.map(impPais, Pais.class);
    }

    public List<Pais> listDtoToEntity(List<ImpPais> impPaiss) {
        return impPaiss.stream()
        		.map(impPais -> DtoToEntity(impPais))
        		.collect(Collectors.toList());
    }

}
