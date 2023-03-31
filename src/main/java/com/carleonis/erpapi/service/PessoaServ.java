package com.carleonis.erpapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carleonis.erpapi.model.Pessoa;
import com.carleonis.erpapi.repository.PessoaRepo;
import com.carleonis.erpapi.service.exception.AcessoDadosVazioException;
import com.carleonis.erpapi.service.exception.DadosInvalidosException;
import com.carleonis.erpapi.service.exception.ViolacaoIntegridadeException;

@Service
public class PessoaServ {

    @Autowired
    private PessoaRepo pessoaRepo;
    
    public Pessoa buscarNomeCpf(String nomeCpf) {
        return pessoaRepo.findByNomeCpf(nomeCpf).orElseThrow(() -> new AcessoDadosVazioException("Pessoa não encontrado"));
    }
    
    public List<Pessoa> clientelike(String like) {
        return pessoaRepo.findByNomeCpfContainingAndCliente(like, true);
    }
    
    public List<Pessoa> fornecedorlike(String like) {
        return pessoaRepo.findByNomeCpfContainingAndFornecedor(like, true);
    }

    public List<Pessoa> listar() {
        return pessoaRepo.findAll();
    }
    public Pessoa buscar(Long id) {
        return pessoaRepo.findById(id).orElseThrow(() -> new AcessoDadosVazioException("Pessoa não encontrado"));
    }

    @Transactional
    public Pessoa adicionar(Pessoa pessoa) {
        try {
        	if (!this.validaCpfCnpj(pessoa.getCpf())) {
        		throw new ViolacaoIntegridadeException("CPF/CNPJ Inválido");
            }
        	pessoa.setCpf(this.formatarCpfCnpj(pessoa.getCpf()));
        	pessoa.setNome(pessoa.getNome().toUpperCase());
        	Optional <Pessoa> pessoaCpf = pessoaRepo.findByCpf(pessoa.getCpf());
        	if (pessoaCpf.isPresent()) {
        		if ((pessoa.isCliente() && pessoaCpf.get().isCliente()) || (pessoa.isFornecedor() && pessoaCpf.get().isFornecedor())) {
        			throw new ViolacaoIntegridadeException("CPF/CNPJ já cadastrado");
        		}
        		if (pessoa.isCliente()) {
        			pessoaCpf.get().setCliente(true);
        			pessoaCpf.get().setNome(pessoa.getNome());
        			pessoaCpf.get().setNomeCpf(pessoa.getNome() + " - " + pessoa.getCpf());
                    return pessoaRepo.save(pessoaCpf.get());
        		}
        		if (pessoa.isFornecedor()) {
        			pessoaCpf.get().setFornecedor(true);
        			pessoaCpf.get().setNome(pessoa.getNome());
        			pessoaCpf.get().setNomeCpf(pessoa.getNome() + " - " + pessoa.getCpf());
                    return pessoaRepo.save(pessoaCpf.get());
        		}
        	}
        	pessoa.setDt_cadastro(LocalDate.now());
        	pessoa.setNomeCpf(pessoa.getNome() + " - " + pessoa.getCpf());
            return pessoaRepo.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public Pessoa alterar(Pessoa pessoa) {
    	Pessoa pessoaEdit = pessoaRepo.findById(pessoa.getId()).orElseThrow(() -> new AcessoDadosVazioException("Pessoa não encontrado"));
        try {
        	if (!this.validaCpfCnpj(pessoa.getCpf())) {
        		throw new ViolacaoIntegridadeException("CPF/CNPJ Inválido");
            }
        	pessoa.setCpf(this.formatarCpfCnpj(pessoa.getCpf()));
        	if (!pessoa.getCpf().equals(pessoaEdit.getCpf())) {
        		Optional <Pessoa> pessoaCpf = pessoaRepo.findByCpf(pessoa.getCpf());
        		if (pessoaCpf.isPresent()) {
        			throw new ViolacaoIntegridadeException("CPF/CNPJ já cadastrado");
        		}
        	}
        	pessoa.setNome(pessoa.getNome().toUpperCase());
        	pessoa.setNomeCpf(pessoa.getNome() + " - " + pessoa.getCpf());
            return pessoaRepo.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException("Erro de Integridade");
        } catch (JpaObjectRetrievalFailureException e) {
            throw new DadosInvalidosException("Dados Inválidos");
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            pessoaRepo.deleteById(id);
            pessoaRepo.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AcessoDadosVazioException(
                String.format("Não existe Entidade com id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeException(
                String.format("Erro de Integridade no id %d", id));
        }
    }
    
    public boolean validaCpfCnpj(String cpfOrCnpj) {
        int i;
        int j;
        int digito;
        int coeficiente;
        int suma;
        int[] Dv = {0, 0};
        if (cpfOrCnpj == null) {
            return false;
        }
        String n = cpfOrCnpj.replaceAll("[^0-9]*", "");
        boolean Cnpj = n.length() == 14;
        boolean Cpf = n.length() == 11;
        if (!Cpf && !Cnpj) {
            return false;
        }
        int dv1 = Integer.parseInt(String.valueOf(n.charAt(n.length() - 2)));
        int dv2 = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));
        for (j = 0; j < 2; j++) {
            suma = 0;
            coeficiente = 2;
            for (i = n.length() - 3 + j; i >= 0; i--) {
                digito = Integer.parseInt(String.valueOf(n.charAt(i)));
                suma += digito * coeficiente;
                coeficiente++;
                if (coeficiente > 9 && Cnpj) {
                    coeficiente = 2;
                }
            }
            Dv[j] = 11 - suma % 11;
            if (Dv[j] >= 10) {
                Dv[j] = 0;
            }
        }
        return dv1 == Dv[0] && dv2 == Dv[1];
    }
    
    public String formatarCpfCnpj(String cpfcnpj) {

        cpfcnpj = removeMascara(cpfcnpj);

        if (cpfcnpj.length() == 11) {
            return String.valueOf(cpfcnpj.charAt(0)) + String.valueOf(cpfcnpj.charAt(1)) + String.valueOf(cpfcnpj.charAt(2)) + "."
                    + String.valueOf(cpfcnpj.charAt(3)) + String.valueOf(cpfcnpj.charAt(4)) + String.valueOf(cpfcnpj.charAt(5)) + "."
                    + String.valueOf(cpfcnpj.charAt(6)) + String.valueOf(cpfcnpj.charAt(7)) + String.valueOf(cpfcnpj.charAt(8)) + "-"
                    + String.valueOf(cpfcnpj.charAt(9)) + String.valueOf(cpfcnpj.charAt(10));
        } else if (cpfcnpj.length() == 14) {
            return String.valueOf(cpfcnpj.charAt(0)) + String.valueOf(cpfcnpj.charAt(1)) + "." + String.valueOf(cpfcnpj.charAt(2))
                    + String.valueOf(cpfcnpj.charAt(3)) + String.valueOf(cpfcnpj.charAt(4)) + "." + String.valueOf(cpfcnpj.charAt(5))
                    + String.valueOf(cpfcnpj.charAt(6)) + String.valueOf(cpfcnpj.charAt(7)) + "/" + String.valueOf(cpfcnpj.charAt(8))
                    + String.valueOf(cpfcnpj.charAt(9)) + String.valueOf(cpfcnpj.charAt(10)) + String.valueOf(cpfcnpj.charAt(11)) + "-"
                    + String.valueOf(cpfcnpj.charAt(12)) + String.valueOf(cpfcnpj.charAt(13));
        }
        return "";
    }
    
    public static String removeMascara(String cpfOrCnpj) {
        return cpfOrCnpj.replaceAll("[^0-9]*", "");
    }


}
