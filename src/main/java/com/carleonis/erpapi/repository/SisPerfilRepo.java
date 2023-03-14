package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.SisPerfil;

@Repository
public interface SisPerfilRepo extends JpaRepository<SisPerfil, Long> {

}
