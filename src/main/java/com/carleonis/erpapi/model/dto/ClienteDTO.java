package com.carleonis.erpapi.model.dto;

import java.time.LocalDate;

import com.carleonis.erpapi.model.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDTO {

    private Long id;

    private String nome;

    private String cpf_cnpj;

    private String email;

    private String fone;

    private ClienteStatus status;

    private LocalDate cadastro;

}
