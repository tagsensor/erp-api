package com.carleonis.erpapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDate;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sis_tbsegur")
public class SisSegur {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_segur")
    private Long id;

    private String usuario;

    private String senha;

    @Column(name = "senha_exp")
    private LocalDate senhaExp;

    private int intervalo;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private SisPerfil idPerfil;

    private boolean bloqueio;

    private String email;

}
