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
import com.carleonis.erpapi.repository.SisPerItensRepo;
import com.carleonis.erpapi.model.SisPerItens;

@Service
public class SisPerItensServ {

    @Autowired
    private SisPerItensRepo sisPerItensRepo;

    public List<SisPerItens> listar() {
        return sisPerItensRepo.findAll();
    }
    public SisPerItens buscar(Long id) {
        return sisPerItensRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("SisPerItens não encontrado"));
    }

    @Transactional
    public SisPerItens adicionar(SisPerItens sisPerItens) {
        try {
            return sisPerItensRepo.save(sisPerItens);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public SisPerItens alterar(SisPerItens sisPerItens) {
        sisPerItensRepo.findById(sisPerItens.getId()).orElseThrow(() -> new AcessoDadosVazioException("SisPerItens não encontrado"));
        try {
            return sisPerItensRepo.save(sisPerItens);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            sisPerItensRepo.deleteById(id);
            sisPerItensRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
