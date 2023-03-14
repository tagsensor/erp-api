package com.carleonis.erpapi.model.dto;

import java.time.LocalDate;

import com.carleonis.erpapi.model.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImpSisSegur {

    private Long id;

    @NotBlank(message = "Informe o nome do Usuário")
    private String usuario;

    private String senha;

    private LocalDate senhaExp;

    private int intervalo;

    private SisPerfil idPerfil;

    private boolean bloqueio;

    @Email(message = "Informe o email do Usuário")
    private String email;

}
