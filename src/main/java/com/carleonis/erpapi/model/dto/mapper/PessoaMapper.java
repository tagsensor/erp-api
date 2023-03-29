package com.carleonis.erpapi.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carleonis.erpapi.model.Pessoa;
import com.carleonis.erpapi.model.dto.ImpPessoa;
import com.carleonis.erpapi.model.dto.OutPessoa;

@Component
public class PessoaMapper{

    @Autowired
    private ModelMapper modelMaper;

    public OutPessoa entityToDto(Pessoa pessoa) {
        return modelMaper.map(pessoa, OutPessoa.class);
    }

    public List<OutPessoa> listEntityToDto(List<Pessoa> pessoas) {
        return pessoas.stream()
        		.map(pessoa -> entityToDto(pessoa))
        		.collect(Collectors.toList());
    }

    public Pessoa DtoToEntity(ImpPessoa impPessoa) {
        return modelMaper.map(impPessoa, Pessoa.class);
    }

    public List<Pessoa> listDtoToEntity(List<ImpPessoa> impPessoas) {
        return impPessoas.stream()
        		.map(impPessoa -> DtoToEntity(impPessoa))
        		.collect(Collectors.toList());
    }

}
