package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.Municipio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutBairro {

    private Long id_bairro;

    private String nome_bairro;

    private Municipio municipio;

}
