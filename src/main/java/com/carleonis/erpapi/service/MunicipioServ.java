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
import com.carleonis.erpapi.repository.MunicipioRepo;
import com.carleonis.erpapi.model.Municipio;
import com.carleonis.erpapi.model.Uf;

@Service
public class MunicipioServ {

    @Autowired
    private MunicipioRepo municipioRepo;

    public List<Municipio> listaByUf(Long id_uf) {
    	Uf uf = new Uf();
    	uf.setId_uf(id_uf);
        return municipioRepo.findByUf(uf);
    }
    public List<Municipio> listar() {
        return municipioRepo.findAll();
    }
    public Municipio buscar(Long id) {
        return municipioRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Municipio não encontrado"));
    }

    @Transactional
    public Municipio adicionar(Municipio municipio) {
        try {
            return municipioRepo.save(municipio);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Municipio alterar(Municipio municipio) {
        municipioRepo.findById(municipio.getId_municipio()).orElseThrow(() -> new AcessoDadosVazioException("Municipio não encontrado"));
        try {
            return municipioRepo.save(municipio);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            municipioRepo.deleteById(id);
            municipioRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
