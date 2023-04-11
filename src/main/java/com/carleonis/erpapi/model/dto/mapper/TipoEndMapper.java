package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.TipoEnd;
import com.carleonis.erpapi.model.dto.ImpTipoEnd;
import com.carleonis.erpapi.model.dto.OutTipoEnd;

@Component
public class TipoEndMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutTipoEnd entityToDto(TipoEnd tipoEnd) {
        return modelMaper.map(tipoEnd, OutTipoEnd.class);
    }

    public List<OutTipoEnd> listEntityToDto(List<TipoEnd> tipoEnds) {
        return tipoEnds.stream()
        		.map(tipoEnd -> entityToDto(tipoEnd))
        		.collect(Collectors.toList());
    }

    public TipoEnd DtoToEntity(ImpTipoEnd impTipoEnd) {
        return modelMaper.map(impTipoEnd, TipoEnd.class);
    }

    public List<TipoEnd> listDtoToEntity(List<ImpTipoEnd> impTipoEnds) {
        return impTipoEnds.stream()
        		.map(impTipoEnd -> DtoToEntity(impTipoEnd))
        		.collect(Collectors.toList());
    }

}
