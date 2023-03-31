package com.carleonis.erpapi.model.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;

import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImpPessoa {

    private Long id;

    @NotBlank(message = "Informe o CPF/CNPJ")
    private String cpf;

    @NotBlank(message = "Informe o nome")
    private String nome;

    private boolean cliente;

    private boolean fornecedor;

    private int versao;

    private String nomeCpf;

    private String identitidade;

    private String ident_emissor;

    private String email;

    private BigDecimal credito;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dt_cadastro;

    private String is_est;

    private String is_mun;

    private String nome_razao;

    private boolean isento_icms;

    private boolean transportadora;

    private int id_segur;

    private BigDecimal credito_pg;

}
