package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.MenuItens;

@Repository
public interface MenuItensRepo extends JpaRepository<MenuItens, Long> {

}
