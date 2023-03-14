package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutMenu {

    private Long id;

    private String nome;

    private String nomeIcone;

    private boolean visivel;

}
