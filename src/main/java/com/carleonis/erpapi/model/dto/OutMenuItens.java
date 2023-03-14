package com.carleonis.erpapi.model.dto;


import com.carleonis.erpapi.model.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutMenuItens {

    private Long id;

    private Menu idMenu;

    private String nome;

    private String pagina;

    private String descricao;

    private int versao;

    private String pagNovo;

    private String paginaHtml;

}
