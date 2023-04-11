package com.carleonis.erpapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carleonis.erpapi.model.Bairro;
import com.carleonis.erpapi.model.Municipio;
import com.carleonis.erpapi.repository.BairroRepo;
import com.carleonis.erpapi.service.exception.AcessoDadosVazioException;
import com.carleonis.erpapi.service.exception.DadosInvalidosException;
import com.carleonis.erpapi.service.exception.ViolacaoIntegridadeException;

@Service
public class BairroServ {

    @Autowired
    private BairroRepo bairroRepo;
    
    public List<Bairro> listaByMunicipio(Long id_municipio) {
    	Municipio mun = new Municipio();
    	mun.setId_municipio(id_municipio);
        return bairroRepo.findByMunicipio(mun);
    }
    public List<Bairro> listar() {
        return bairroRepo.findAll();
    }
    public Bairro buscar(Long id) {
        return bairroRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Bairro não encontrado"));
    }

    @Transactional
    public Bairro adicionar(Bairro bairro) {
        try {
            return bairroRepo.save(bairro);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Bairro alterar(Bairro bairro) {
        bairroRepo.findById(bairro.getId_bairro()).orElseThrow(() -> new AcessoDadosVazioException("Bairro não encontrado"));
        try {
            return bairroRepo.save(bairro);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            bairroRepo.deleteById(id);
            bairroRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
