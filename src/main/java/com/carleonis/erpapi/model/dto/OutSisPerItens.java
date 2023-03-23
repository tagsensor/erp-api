package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutSisPerItens {

    private Long id;

    private SisPerfil perfil;

    private String pagina;

    private int valor;

    private int idSub;

}
