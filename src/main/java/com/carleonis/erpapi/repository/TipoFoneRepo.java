package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.TipoFone;

@Repository
public interface TipoFoneRepo extends JpaRepository<TipoFone, Long> {

}
