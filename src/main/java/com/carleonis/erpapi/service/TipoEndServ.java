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
import com.carleonis.erpapi.repository.TipoEndRepo;
import com.carleonis.erpapi.model.TipoEnd;

@Service
public class TipoEndServ {

    @Autowired
    private TipoEndRepo tipoEndRepo;

    public List<TipoEnd> listar() {
        return tipoEndRepo.findAll();
    }
    public TipoEnd buscar(Long id) {
        return tipoEndRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("TipoEnd não encontrado"));
    }

    @Transactional
    public TipoEnd adicionar(TipoEnd tipoEnd) {
        try {
            return tipoEndRepo.save(tipoEnd);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public TipoEnd alterar(TipoEnd tipoEnd) {
        tipoEndRepo.findById(tipoEnd.getId_tipo_end()).orElseThrow(() -> new AcessoDadosVazioException("TipoEnd não encontrado"));
        try {
            return tipoEndRepo.save(tipoEnd);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            tipoEndRepo.deleteById(id);
            tipoEndRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
