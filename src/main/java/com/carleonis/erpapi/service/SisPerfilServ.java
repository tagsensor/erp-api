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
import com.carleonis.erpapi.repository.SisPerfilRepo;
import com.carleonis.erpapi.model.SisPerfil;

@Service
public class SisPerfilServ {

    @Autowired
    private SisPerfilRepo sisPerfilRepo;

    public List<SisPerfil> listar() {
        return sisPerfilRepo.findAll();
    }
    public SisPerfil buscar(Long id) {
        return sisPerfilRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("SisPerfil não encontrado"));
    }

    @Transactional
    public SisPerfil adicionar(SisPerfil sisPerfil) {
        try {
            return sisPerfilRepo.save(sisPerfil);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public SisPerfil alterar(SisPerfil sisPerfil) {
        sisPerfilRepo.findById(sisPerfil.getId()).orElseThrow(() -> new AcessoDadosVazioException("SisPerfil não encontrado"));
        try {
            return sisPerfilRepo.save(sisPerfil);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            sisPerfilRepo.deleteById(id);
            sisPerfilRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
