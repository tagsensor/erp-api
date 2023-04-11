package com.carleonis.erpapi.model.dto;


import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImpPais {

    private Long id_pais;

    private String codPais;

    private String nomePais;

}
