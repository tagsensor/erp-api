package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.SisPerItens;

@Repository
public interface SisPerItensRepo extends JpaRepository<SisPerItens, Long> {
	
	Optional<SisPerItens> findByIdSubAndPerfilId(int idSub, Long IdPerfil);
	//Optional<SisPerItens> findByIdSub(int idSub);

}
