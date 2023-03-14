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
import com.carleonis.erpapi.repository.SisSegurRepo;
import com.carleonis.erpapi.model.SisSegur;

@Service
public class SisSegurServ {

    @Autowired
    private SisSegurRepo sissegurRepo;

    public List<SisSegur> listar() {
        return sissegurRepo.findAll();
    }
    public SisSegur buscar(Long id) {
        return sissegurRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("SisSegur não encontrado"));
    }

    @Transactional
    public SisSegur adicionar(SisSegur sissegur) {
        try {
            return sissegurRepo.save(sissegur);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public SisSegur alterar(SisSegur sissegur) {
        sissegurRepo.findById(sissegur.getId()).orElseThrow(() -> new AcessoDadosVazioException("SisSegur não encontrado"));
        try {
            return sissegurRepo.save(sissegur);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            sissegurRepo.deleteById(id);
            sissegurRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
