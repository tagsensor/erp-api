package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.SisPerItens;
import com.carleonis.erpapi.model.dto.ImpSisPerItens;
import com.carleonis.erpapi.model.dto.OutSisPerItens;

@Component
public class SisPerItensMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutSisPerItens entityToDto(SisPerItens sisPerItens) {
        return modelMaper.map(sisPerItens, OutSisPerItens.class);
    }

    public List<OutSisPerItens> listEntityToDto(List<SisPerItens> sisPerItenss) {
        return sisPerItenss.stream()
        		.map(sisPerItens -> entityToDto(sisPerItens))
        		.collect(Collectors.toList());
    }

    public SisPerItens DtoToEntity(ImpSisPerItens impSisPeritens) {
        return modelMaper.map(impSisPeritens, SisPerItens.class);
    }

    public List<SisPerItens> listDtoToEntity(List<ImpSisPerItens> impSisPeritenss) {
        return impSisPeritenss.stream()
        		.map(impSisPeritens -> DtoToEntity(impSisPeritens))
        		.collect(Collectors.toList());
    }

}
