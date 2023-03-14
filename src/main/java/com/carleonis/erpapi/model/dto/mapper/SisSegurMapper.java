package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.SisSegur;
import com.carleonis.erpapi.model.dto.ImpSisSegur;
import com.carleonis.erpapi.model.dto.OutSisSegur;

@Component
public class SisSegurMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutSisSegur entityToDto(SisSegur sissegur) {
        return modelMaper.map(sissegur, OutSisSegur.class);
    }

    public List<OutSisSegur> listEntityToDto(List<SisSegur> sissegurs) {
        return sissegurs.stream()
        		.map(sissegur -> entityToDto(sissegur))
        		.collect(Collectors.toList());
    }

    public SisSegur DtoToEntity(ImpSisSegur impSisSegur) {
        return modelMaper.map(impSisSegur, SisSegur.class);
    }

    public List<SisSegur> listDtoToEntity(List<ImpSisSegur> impSisSegurs) {
        return impSisSegurs.stream()
        		.map(impSisSegur -> DtoToEntity(impSisSegur))
        		.collect(Collectors.toList());
    }

}
