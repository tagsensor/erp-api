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
import com.carleonis.erpapi.repository.EnderecoRepo;
import com.carleonis.erpapi.model.Endereco;
import com.carleonis.erpapi.model.Pessoa;

@Service
public class EnderecoServ {

    @Autowired
    private EnderecoRepo enderecoRepo;

    public List<Endereco> buscaByPessoa(Long id) {
        return enderecoRepo.findByPessoaId(id);
    }
    public List<Endereco> listar() {
        return enderecoRepo.findAll();
    }
    public Endereco buscar(Long id) {
        return enderecoRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Endereco não encontrado"));
    }

    @Transactional
    public Endereco adicionar(Endereco endereco) {
        try {
            return enderecoRepo.save(endereco);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Endereco alterar(Endereco endereco) {
        enderecoRepo.findById(endereco.getId_end()).orElseThrow(() -> new AcessoDadosVazioException("Endereco não encontrado"));
        try {
            return enderecoRepo.save(endereco);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            enderecoRepo.deleteById(id);
            enderecoRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
