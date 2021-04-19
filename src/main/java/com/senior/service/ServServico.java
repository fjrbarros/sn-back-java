package com.senior.service;

import java.util.List;
import java.util.Optional;

import com.senior.entity.Servico;
import com.senior.error.ResourceNotFound;
import com.senior.repository.RepServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServServico {

    private final RepServico repServico;

    @Autowired
    public ServServico(RepServico repServico) {
        this.repServico = repServico;
    }

    public Servico salvarServico(Servico servico) {
        if (servico.getNomeServico().isEmpty()) {
            throw new ResourceNotFound("Nome do servico não informado!");
        }

        if (servico.getValorHora() == 0) {
            throw new ResourceNotFound("Valor por hora não informado");
        }

        return repServico.saveAndFlush(servico);
    }

    public Servico atualizarServico(Servico servico) {

        validaIdServico(servico.getIdServico());

        Servico servicoAtualizado = repServico.saveAndFlush(servico);

        if (servicoAtualizado == null) {
            throw new ResourceNotFound("Erro ao atualizar serviço");
        }

        return servicoAtualizado;
    }

    public String removerServico(long id) {

        Optional<Servico> findServico = recuperarServicoId(id);

        if (!findServico.isPresent()) {
            throw new ResourceNotFound("Registro não cadastrado!");
        }

        repServico.deleteById(id);

        return "Serviço removido com sucesso!";
    }

    public List<Servico> recuperarServicos() {
        return repServico.findAll();
    }

    public Optional<Servico> recuperarServicoId(long id) {
        validaIdServico(id);

        Optional<Servico> findServico = repServico.findById(id);

        if (!findServico.isPresent()) {
            throw new ResourceNotFound("Registro não cadastrado!");
        }

        return findServico;
    }

    private void validaIdServico(long id) {
        if (id == 0) {
            throw new ResourceNotFound("Id do serviço não informado");
        }
    }
}
