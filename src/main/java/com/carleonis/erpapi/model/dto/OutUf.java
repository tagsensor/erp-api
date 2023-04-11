package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.Pais;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutUf {

    private Long id_uf;

    private String cod_uf;

    private String nome_uf;

    private Pais pais;

    private String sigla_uf;

}
