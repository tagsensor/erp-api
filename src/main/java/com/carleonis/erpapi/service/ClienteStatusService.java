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
import com.carleonis.erpapi.repository.ClienteStatusRepository;
import com.carleonis.erpapi.model.ClienteStatus;

@Service
public class ClienteStatusService {

    @Autowired
    private ClienteStatusRepository clienteStatusRepository;

    public List<ClienteStatus> listar() {
        return clienteStatusRepository.findAll();
    }
    public ClienteStatus buscar(Long id) {
        return clienteStatusRepository.findById(id).orElseThrow(() -> new AcessoDadosVazioException("ClienteStatus não encontrado"));
    }

    @Transactional
    public ClienteStatus adicionar(ClienteStatus clienteStatus) {
        try {
            return clienteStatusRepository.save(clienteStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public ClienteStatus alterar(ClienteStatus clienteStatus) {
        clienteStatusRepository.findById(clienteStatus.getId()).orElseThrow(() -> new AcessoDadosVazioException("ClienteStatus não encontrado"));
        try {
            return clienteStatusRepository.save(clienteStatus);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            clienteStatusRepository.deleteById(id);
            clienteStatusRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }

}
