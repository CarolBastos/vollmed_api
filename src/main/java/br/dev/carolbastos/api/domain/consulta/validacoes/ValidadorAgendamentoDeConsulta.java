package br.dev.carolbastos.api.domain.consulta.validacoes;

import br.dev.carolbastos.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
