package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.Contato;
import com.carleonis.erpapi.model.Endereco;

@Repository
public interface ContatoRepo extends JpaRepository<Contato, Long> {

	List<Contato> findByPessoaId(Long id);
}
