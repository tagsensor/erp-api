package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.Bairro;
import com.carleonis.erpapi.model.Municipio;
import com.carleonis.erpapi.model.Pessoa;
import com.carleonis.erpapi.model.TipoEnd;
import com.carleonis.erpapi.model.Uf;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImpEndereco {

    private Long id_end;

    private int versao;

    private Pessoa pessoa;

    private TipoEnd tipoEnd;

    private String cep;

    private String logradouro;

    private String num;

    private String complemento;

    private Bairro bairro;

    private Municipio municipio;

    private Uf uf;

    private boolean correspondencia;

}
