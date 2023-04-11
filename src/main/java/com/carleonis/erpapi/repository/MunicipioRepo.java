package com.carleonis.erpapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carleonis.erpapi.model.Municipio;
import com.carleonis.erpapi.model.Uf;

@Repository
public interface MunicipioRepo extends JpaRepository<Municipio, Long> {
	
	List<Municipio> findByUf(Uf uf);

}
