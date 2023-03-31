package com.carleonis.erpapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carleonis.erpapi.model.Pessoa;

@Repository
public interface PessoaRepo extends JpaRepository<Pessoa, Long> {
	
	List<Pessoa> findByNomeCpfContainingAndCliente(String nomeCpf, boolean cliente);
	
	List<Pessoa> findByNomeCpfContainingAndFornecedor(String nomeCpf, boolean fornecedor);
	
	Optional<Pessoa> findByNomeCpf(String nomeCpf);
	
	Optional<Pessoa> findByCpf(String cpf);

}
