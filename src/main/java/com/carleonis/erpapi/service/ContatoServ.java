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
import com.carleonis.erpapi.repository.ContatoRepo;
import com.carleonis.erpapi.model.Contato;
import com.carleonis.erpapi.model.Endereco;

@Service
public class ContatoServ {

    @Autowired
    private ContatoRepo contatoRepo;
    
    public List<Contato> buscaByPessoa(Long id) {
        return contatoRepo.findByPessoaId(id);
    }
    public List<Contato> listar() {
        return contatoRepo.findAll();
    }
    public Contato buscar(Long id) {
        return contatoRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Contato não encontrado"));
    }

    @Transactional
    public Contato adicionar(Contato contato) {
        try {
            return contatoRepo.save(contato);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Contato alterar(Contato contato) {
        contatoRepo.findById(contato.getId_telefone()).orElseThrow(() -> new AcessoDadosVazioException("Contato não encontrado"));
        try {
            return contatoRepo.save(contato);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            contatoRepo.deleteById(id);
            contatoRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
