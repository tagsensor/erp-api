package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Bairro;
import com.carleonis.erpapi.model.dto.ImpBairro;
import com.carleonis.erpapi.model.dto.OutBairro;

@Component
public class BairroMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutBairro entityToDto(Bairro bairro) {
        return modelMaper.map(bairro, OutBairro.class);
    }

    public List<OutBairro> listEntityToDto(List<Bairro> bairros) {
        return bairros.stream()
        		.map(bairro -> entityToDto(bairro))
        		.collect(Collectors.toList());
    }

    public Bairro DtoToEntity(ImpBairro impBairro) {
        return modelMaper.map(impBairro, Bairro.class);
    }

    public List<Bairro> listDtoToEntity(List<ImpBairro> impBairros) {
        return impBairros.stream()
        		.map(impBairro -> DtoToEntity(impBairro))
        		.collect(Collectors.toList());
    }

}
