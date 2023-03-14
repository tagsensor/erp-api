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
import com.carleonis.erpapi.repository.MenuRepo;
import com.carleonis.erpapi.model.Menu;

@Service
public class MenuServ {

    @Autowired
    private MenuRepo menuRepo;

    public List<Menu> listar() {
        return menuRepo.findAll();
    }
    public Menu buscar(Long id) {
        return menuRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Menu não encontrado"));
    }

    @Transactional
    public Menu adicionar(Menu menu) {
        try {
            return menuRepo.save(menu);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Menu alterar(Menu menu) {
        menuRepo.findById(menu.getId()).orElseThrow(() -> new AcessoDadosVazioException("Menu não encontrado"));
        try {
            return menuRepo.save(menu);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            menuRepo.deleteById(id);
            menuRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
