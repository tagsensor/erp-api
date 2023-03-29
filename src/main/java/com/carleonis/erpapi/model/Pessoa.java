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

    private boolean funcionario;

    private boolean comissionado;

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

    private boolean proprietario;

    private String matricula;

    private String nome_abrev;

    private int id_situacao;

    private LocalDate dt_nascimento;

    private String nome_pai;

    private String nome_mae;

    private int id_est_civil;

    private String sexo;

    private String nacionalidade;

    private String local_nascimento;

    private String doc_rg;

    private String doc_ctps;

    private String doc_pis_pasep;

    private String doc_tit_eleitoral;

    private String doc_cert_reservista;

    private String doc_cnh;

    private String doc_cat_cnh;

    private LocalDate dt_venc_cnh;

    private boolean contabilidade;

    private boolean motorista;

    private int id_rh_form;

    private String rh_prof;

    private String rh_prof_cbo;

    private int id_tipo_dest;

    private boolean paf;

    private boolean paf1;

}
