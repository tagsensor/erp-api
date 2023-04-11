package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.TipoEnd;

@Repository
public interface TipoEndRepo extends JpaRepository<TipoEnd, Long> {

}
