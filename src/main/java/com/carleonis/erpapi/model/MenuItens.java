package com.carleonis.erpapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sis_menu_sub")
public class MenuItens {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu idMenu;

    private String nome;

    private String pagina;

    private String descricao;

    private int versao;

    @Column(name = "pag_novo")
    private String pagNovo;

    @Column(name = "pagina_html")
    private String paginaHtml;

}
