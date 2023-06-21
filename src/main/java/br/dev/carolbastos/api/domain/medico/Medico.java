package br.dev.carolbastos.api.domain.medico;

import br.dev.carolbastos.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Classe que representam os atributos da ENTIDADE JPA Medico

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter //Notação do Lombok que gera os metodos getters
@NoArgsConstructor // Notação do Lombok, gera o construtor default sem argumentos que a JPA exige em todas as entidades
@AllArgsConstructor //Notação do Lombok, gera um construtor que recebe todos os campos
@EqualsAndHashCode(of = "id") // gera o equals e o hashcode em cima do id
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded //Embeddable Attribute da JPA, fica em uma classe separada mas no
    // banco de dados ele considera que os campos dessa classe fazem parte da mesma tabela de médicos
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
