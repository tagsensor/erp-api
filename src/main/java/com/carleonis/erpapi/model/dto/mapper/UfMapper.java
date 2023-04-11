package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Uf;
import com.carleonis.erpapi.model.dto.ImpUf;
import com.carleonis.erpapi.model.dto.OutUf;

@Component
public class UfMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutUf entityToDto(Uf uf) {
        return modelMaper.map(uf, OutUf.class);
    }

    public List<OutUf> listEntityToDto(List<Uf> ufs) {
        return ufs.stream()
        		.map(uf -> entityToDto(uf))
        		.collect(Collectors.toList());
    }

    public Uf DtoToEntity(ImpUf impUf) {
        return modelMaper.map(impUf, Uf.class);
    }

    public List<Uf> listDtoToEntity(List<ImpUf> impUfs) {
        return impUfs.stream()
        		.map(impUf -> DtoToEntity(impUf))
        		.collect(Collectors.toList());
    }

}
