package com.carleonis.erpapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sis_menu")
public class Menu {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long id;

    private String nome;

    @Column(name = "nome_icone")
    private String nomeIcone;

    private boolean visivel;
    
    @OneToMany
    @JoinColumn(name = "id_menu") // Esta coluna está na tabela "aluno".
    private List<MenuItens> subMenu;

}
