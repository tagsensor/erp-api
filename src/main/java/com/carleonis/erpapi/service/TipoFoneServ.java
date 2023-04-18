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
import com.carleonis.erpapi.repository.TipoFoneRepo;
import com.carleonis.erpapi.model.TipoFone;

@Service
public class TipoFoneServ {

    @Autowired
    private TipoFoneRepo tipoFoneRepo;

    public List<TipoFone> listar() {
        return tipoFoneRepo.findAll();
    }
    public TipoFone buscar(Long id) {
        return tipoFoneRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("TipoFone não encontrado"));
    }

    @Transactional
    public TipoFone adicionar(TipoFone tipoFone) {
        try {
            return tipoFoneRepo.save(tipoFone);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public TipoFone alterar(TipoFone tipoFone) {
        tipoFoneRepo.findById(tipoFone.getId_tipo_fone()).orElseThrow(() -> new AcessoDadosVazioException("TipoFone não encontrado"));
        try {
            return tipoFoneRepo.save(tipoFone);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            tipoFoneRepo.deleteById(id);
            tipoFoneRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
