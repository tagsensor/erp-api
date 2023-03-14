package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.MenuItens;
import com.carleonis.erpapi.model.dto.ImpMenuItens;
import com.carleonis.erpapi.model.dto.OutMenuItens;

@Component
public class MenuItensMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutMenuItens entityToDto(MenuItens menuItens) {
        return modelMaper.map(menuItens, OutMenuItens.class);
    }

    public List<OutMenuItens> listEntityToDto(List<MenuItens> menuItenss) {
        return menuItenss.stream()
        		.map(menuItens -> entityToDto(menuItens))
        		.collect(Collectors.toList());
    }

    public MenuItens DtoToEntity(ImpMenuItens impMenuitens) {
        return modelMaper.map(impMenuitens, MenuItens.class);
    }

    public List<MenuItens> listDtoToEntity(List<ImpMenuItens> impMenuitenss) {
        return impMenuitenss.stream()
        		.map(impMenuitens -> DtoToEntity(impMenuitens))
        		.collect(Collectors.toList());
    }

}
