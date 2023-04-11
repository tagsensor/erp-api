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
import com.carleonis.erpapi.repository.UfRepo;
import com.carleonis.erpapi.model.Uf;

@Service
public class UfServ {

    @Autowired
    private UfRepo ufRepo;

    public List<Uf> listar() {
        return ufRepo.findAll();
    }
    public Uf buscar(Long id) {
        return ufRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Uf não encontrado"));
    }

    @Transactional
    public Uf adicionar(Uf uf) {
        try {
            return ufRepo.save(uf);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Uf alterar(Uf uf) {
        ufRepo.findById(uf.getId_uf()).orElseThrow(() -> new AcessoDadosVazioException("Uf não encontrado"));
        try {
            return ufRepo.save(uf);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            ufRepo.deleteById(id);
            ufRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
