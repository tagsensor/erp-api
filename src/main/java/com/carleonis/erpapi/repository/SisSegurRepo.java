package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.SisSegur;

@Repository
public interface SisSegurRepo extends JpaRepository<SisSegur, Long> {

}
