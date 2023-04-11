package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.Uf;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImpMunicipio {

    private Long id_municipio;

    private String cod_mun;

    private String nome_mun;

    private Uf uf;

}
