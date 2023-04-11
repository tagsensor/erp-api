package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.Endereco;
import com.carleonis.erpapi.model.Pessoa;

@Repository
public interface EnderecoRepo extends JpaRepository<Endereco, Long> {
	
	//List<Endereco> findByPessoa(Pessoa pessoa); funcionou
	
	List<Endereco> findByPessoaId(Long id);

}
