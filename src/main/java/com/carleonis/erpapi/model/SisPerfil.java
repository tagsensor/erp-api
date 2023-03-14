package com.carleonis.erpapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sis_perfil")
public class SisPerfil {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_perfil")
    private Long id;

    private String descricao;

}
