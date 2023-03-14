package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Menu;
import com.carleonis.erpapi.model.dto.ImpMenu;
import com.carleonis.erpapi.model.dto.OutMenu;

@Component
public class MenuMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutMenu entityToDto(Menu menu) {
        return modelMaper.map(menu, OutMenu.class);
    }

    public List<OutMenu> listEntityToDto(List<Menu> menus) {
        return menus.stream()
        		.map(menu -> entityToDto(menu))
        		.collect(Collectors.toList());
    }

    public Menu DtoToEntity(ImpMenu impMenu) {
        return modelMaper.map(impMenu, Menu.class);
    }

    public List<Menu> listDtoToEntity(List<ImpMenu> impMenus) {
        return impMenus.stream()
        		.map(impMenu -> DtoToEntity(impMenu))
        		.collect(Collectors.toList());
    }

}
