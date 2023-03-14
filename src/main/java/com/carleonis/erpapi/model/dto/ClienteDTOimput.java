package com.carleonis.erpapi.model.dto;

import java.time.LocalDate;

import com.carleonis.erpapi.model.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDTOimput {

    private Long id;

    @NotBlank(message = "Informe o nome do Cliente")
    private String nome;

    private String cpf_cnpj;

    private String email;

    private String fone;

    private ClienteStatus status;

    private LocalDate cadastro;

}
