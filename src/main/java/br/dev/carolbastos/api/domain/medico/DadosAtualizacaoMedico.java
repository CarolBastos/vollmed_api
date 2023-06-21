package br.dev.carolbastos.api.domain.medico;

import br.dev.carolbastos.api.domain.endereco.DadosEndereco;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

//Usa o NotNull quando não for string
public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
