package com.carleonis.erpapi.model.dto;

import java.time.LocalDate;

import com.carleonis.erpapi.model.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutSisSegur {

    private Long id;

    private String usuario;

    private String senha;

    private LocalDate senhaExp;

    private int intervalo;

    private SisPerfil idPerfil;

    private boolean bloqueio;

    private String email;

}
