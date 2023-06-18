package br.dev.carolbastos.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.uf = endereco.uf();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
        this.cep = endereco.cep();
    }

    public void atualizarInformacoes(DadosEndereco endereco) {
        if(endereco.logradouro() != null){
            this.logradouro = endereco.logradouro();
        }
        if(endereco.uf() != null){
            this.uf = endereco.uf();
        }
        if(endereco.bairro() != null){
            this.bairro = endereco.bairro();
        }
        if(endereco.cidade() != null){
            this.cidade = endereco.cidade();
        }
        if(endereco.numero() != null){
            this.numero = endereco.numero();
        }
        if(endereco.cep() != null){
            this.cep = endereco.cep();
        }
        if(endereco.complemento() != null){
            this.complemento = endereco.complemento();
        }
    }
}
