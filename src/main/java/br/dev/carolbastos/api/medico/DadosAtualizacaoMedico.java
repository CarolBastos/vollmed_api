package br.dev.carolbastos.api.medico;

import br.dev.carolbastos.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

//Usa o NotNull quando não for string
public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
