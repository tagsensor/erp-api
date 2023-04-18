package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.TipoFone;
import com.carleonis.erpapi.model.dto.ImpTipoFone;
import com.carleonis.erpapi.model.dto.OutTipoFone;

@Component
public class TipoFoneMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutTipoFone entityToDto(TipoFone tipoFone) {
        return modelMaper.map(tipoFone, OutTipoFone.class);
    }

    public List<OutTipoFone> listEntityToDto(List<TipoFone> tipoFones) {
        return tipoFones.stream()
        		.map(tipoFone -> entityToDto(tipoFone))
        		.collect(Collectors.toList());
    }

    public TipoFone DtoToEntity(ImpTipoFone impTipoFone) {
        return modelMaper.map(impTipoFone, TipoFone.class);
    }

    public List<TipoFone> listDtoToEntity(List<ImpTipoFone> impTipoFones) {
        return impTipoFones.stream()
        		.map(impTipoFone -> DtoToEntity(impTipoFone))
        		.collect(Collectors.toList());
    }

}
