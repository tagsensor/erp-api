package com.carleonis.erpapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cad_pessoa")
public class Pessoa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;

    private String cpf;

    private String nome;

    private boolean cliente;

    private boolean fornecedor;

    private int versao;

    @Column(name = "nome_cpf")
    private String nomeCpf;

    private String identitidade;

    private String ident_emissor;

    private String email;

    private BigDecimal credito;

    private LocalDate dt_cadastro;

    private String is_est;

    private String is_mun;

    private String nome_razao;

    private boolean isento_icms;

    private boolean transportadora;

    private int id_segur;

    private BigDecimal credito_pg;

}
