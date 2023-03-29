package com.carleonis.erpapi.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.carleonis.erpapi.service.PessoaServ;
import com.carleonis.erpapi.model.dto.ImpPessoa;
import com.carleonis.erpapi.model.dto.OutPessoa;
import com.carleonis.erpapi.model.dto.mapper.PessoaMapper;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins="*")
public class PessoaCtrl {

    @Autowired
    private PessoaServ pessoaServ;

    @Autowired
    private PessoaMapper pessoaMapper;
    
    /*
     * não está em uso por enquanto
    @PostMapping("/nomeCpf")
    public OutPessoa buscaNomeCpf(@RequestBody ImpPessoa impPessoa) {
        return pessoaMapper.entityToDto(pessoaServ.buscarNomeCpf(impPessoa.getNomeCpf()));
    }
	 */
    
    @PostMapping("/clientelike")
    public List<OutPessoa> clientelike(@RequestBody ImpPessoa impPessoa) {
        return pessoaMapper.listEntityToDto(pessoaServ.clientelike(impPessoa.getNomeCpf()));
    }
    
    @PostMapping("/fornecedorlike")
    public List<OutPessoa> fornecedorlike(@RequestBody ImpPessoa impPessoa) {
        return pessoaMapper.listEntityToDto(pessoaServ.fornecedorlike(impPessoa.getNomeCpf()));
    }
    
    @GetMapping
    public List<OutPessoa> listar() {
        return pessoaMapper.listEntityToDto(pessoaServ.listar());
    }

    @GetMapping("/{id}")
    public OutPessoa buscar(@PathVariable Long id) {
        return pessoaMapper.entityToDto(pessoaServ.buscar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OutPessoa adicionar(@RequestBody @Valid ImpPessoa impPessoa) {
        return pessoaMapper.entityToDto(pessoaServ.adicionar(pessoaMapper.DtoToEntity(impPessoa)));
    }

    @PutMapping
    public OutPessoa alterar(@RequestBody @Valid ImpPessoa impPessoa) {
        return pessoaMapper.entityToDto(pessoaServ.alterar(pessoaMapper.DtoToEntity(impPessoa)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pessoaServ.deletar(id);
    }

}
