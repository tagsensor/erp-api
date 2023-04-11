package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.Bairro;
import com.carleonis.erpapi.model.Municipio;
import com.carleonis.erpapi.model.Uf;

@Repository
public interface BairroRepo extends JpaRepository<Bairro, Long> {
	
	List<Bairro> findByMunicipio(Municipio municipio);

}
