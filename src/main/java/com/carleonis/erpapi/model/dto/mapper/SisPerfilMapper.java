package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.SisPerfil;
import com.carleonis.erpapi.model.dto.ImpSisPerfil;
import com.carleonis.erpapi.model.dto.OutSisPerfil;

@Component
public class SisPerfilMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutSisPerfil entityToDto(SisPerfil sisPerfil) {
        return modelMaper.map(sisPerfil, OutSisPerfil.class);
    }

    public List<OutSisPerfil> listEntityToDto(List<SisPerfil> sisPerfils) {
        return sisPerfils.stream()
        		.map(sisPerfil -> entityToDto(sisPerfil))
        		.collect(Collectors.toList());
    }

    public SisPerfil DtoToEntity(ImpSisPerfil impSisPerfil) {
        return modelMaper.map(impSisPerfil, SisPerfil.class);
    }

    public List<SisPerfil> listDtoToEntity(List<ImpSisPerfil> impSisPerfils) {
        return impSisPerfils.stream()
        		.map(impSisPerfil -> DtoToEntity(impSisPerfil))
        		.collect(Collectors.toList());
    }

}
