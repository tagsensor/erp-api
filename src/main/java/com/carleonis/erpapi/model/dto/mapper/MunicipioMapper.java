package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Municipio;
import com.carleonis.erpapi.model.dto.ImpMunicipio;
import com.carleonis.erpapi.model.dto.OutMunicipio;

@Component
public class MunicipioMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutMunicipio entityToDto(Municipio municipio) {
        return modelMaper.map(municipio, OutMunicipio.class);
    }

    public List<OutMunicipio> listEntityToDto(List<Municipio> municipios) {
        return municipios.stream()
        		.map(municipio -> entityToDto(municipio))
        		.collect(Collectors.toList());
    }

    public Municipio DtoToEntity(ImpMunicipio impMunicipio) {
        return modelMaper.map(impMunicipio, Municipio.class);
    }

    public List<Municipio> listDtoToEntity(List<ImpMunicipio> impMunicipios) {
        return impMunicipios.stream()
        		.map(impMunicipio -> DtoToEntity(impMunicipio))
        		.collect(Collectors.toList());
    }

}
