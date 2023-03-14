package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImpSisPerItens {

    private Long id;

    private SisPerfil idPerfil;

    private int idChave;

    private String pagina;

    private int valor;

    private int idSub;

}
