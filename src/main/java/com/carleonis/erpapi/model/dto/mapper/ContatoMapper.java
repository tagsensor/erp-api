package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Contato;
import com.carleonis.erpapi.model.dto.ImpContato;
import com.carleonis.erpapi.model.dto.OutContato;

@Component
public class ContatoMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutContato entityToDto(Contato contato) {
        return modelMaper.map(contato, OutContato.class);
    }

    public List<OutContato> listEntityToDto(List<Contato> contatos) {
        return contatos.stream()
        		.map(contato -> entityToDto(contato))
        		.collect(Collectors.toList());
    }

    public Contato DtoToEntity(ImpContato impContato) {
        return modelMaper.map(impContato, Contato.class);
    }

    public List<Contato> listDtoToEntity(List<ImpContato> impContatos) {
        return impContatos.stream()
        		.map(impContato -> DtoToEntity(impContato))
        		.collect(Collectors.toList());
    }

}
