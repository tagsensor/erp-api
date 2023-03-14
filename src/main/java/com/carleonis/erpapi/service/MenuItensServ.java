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
import com.carleonis.erpapi.repository.MenuItensRepo;
import com.carleonis.erpapi.model.MenuItens;

@Service
public class MenuItensServ {

    @Autowired
    private MenuItensRepo menuItensRepo;

    public List<MenuItens> listar() {
        return menuItensRepo.findAll();
    }
    public MenuItens buscar(Long id) {
        return menuItensRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("MenuItens não encontrado"));
    }

    @Transactional
    public MenuItens adicionar(MenuItens menuItens) {
        try {
            return menuItensRepo.save(menuItens);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public MenuItens alterar(MenuItens menuItens) {
        menuItensRepo.findById(menuItens.getId()).orElseThrow(() -> new AcessoDadosVazioException("MenuItens não encontrado"));
        try {
            return menuItensRepo.save(menuItens);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            menuItensRepo.deleteById(id);
            menuItensRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
