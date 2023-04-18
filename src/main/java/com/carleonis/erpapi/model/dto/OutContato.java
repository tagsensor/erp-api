package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.Pessoa;
import com.carleonis.erpapi.model.TipoFone;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutContato {

    private Long id_telefone;

    private int versao;

    private TipoFone tipoFone;

    private Pessoa pessoa;

    private int ddd;

    private int numero;

    private int ramal;

    private String contato;

    private boolean principal;

    private String email;

}
