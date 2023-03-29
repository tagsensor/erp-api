package com.carleonis.erpapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.carleonis.erpapi.service.exception.AcessoDadosVazioException;
import com.carleonis.erpapi.service.exception.ViolacaoIntegridadeException;
import com.carleonis.erpapi.service.exception.DadosInvalidosException;
import com.carleonis.erpapi.repository.PessoaRepo;
import com.carleonis.erpapi.model.Pessoa;

@Service
public class PessoaServ {

    @Autowired
    private PessoaRepo pessoaRepo;
    
    public Pessoa buscarNomeCpf(String nomeCpf) {
        return pessoaRepo.findByNomeCpf(nomeCpf).orElseThrow(() -> new AcessoDadosVazioException("Pessoa não encontrado"));
    }
    
    public List<Pessoa> clientelike(String like) {
        return pessoaRepo.findByNomeCpfContainingAndCliente(like, true);
    }
    
    public List<Pessoa> fornecedorlike(String like) {
        return pessoaRepo.findByNomeCpfContainingAndFornecedor(like, true);
    }

    public List<Pessoa> listar() {
        return pessoaRepo.findAll();
    }
    public Pessoa buscar(Long id) {
        return pessoaRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Pessoa não encontrado"));
    }

    @Transactional
    public Pessoa adicionar(Pessoa pessoa) {
        try {
            return pessoaRepo.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Pessoa alterar(Pessoa pessoa) {
        pessoaRepo.findById(pessoa.getId()).orElseThrow(() -> new AcessoDadosVazioException("Pessoa não encontrado"));
        try {
            return pessoaRepo.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            pessoaRepo.deleteById(id);
            pessoaRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
