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
import com.carleonis.erpapi.repository.PaisRepo;
import com.carleonis.erpapi.model.Pais;

@Service
public class PaisServ {

    @Autowired
    private PaisRepo paisRepo;

    public List<Pais> listar() {
        return paisRepo.findAll();
    }
    public Pais buscar(Long id) {
        return paisRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Pais não encontrado"));
    }

    @Transactional
    public Pais adicionar(Pais pais) {
        try {
            return paisRepo.save(pais);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Pais alterar(Pais pais) {
        paisRepo.findById(pais.getId_pais()).orElseThrow(() -> new AcessoDadosVazioException("Pais não encontrado"));
        try {
            return paisRepo.save(pais);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            paisRepo.deleteById(id);
            paisRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
