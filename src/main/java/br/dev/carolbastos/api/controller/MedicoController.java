package br.dev.carolbastos.api.controller;

import br.dev.carolbastos.api.endereco.Endereco;
import br.dev.carolbastos.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Valid: Vem do Bean Validation e faz validacoes dos atributos de um Record
@RestController
@RequestMapping("medicos") //mapeia para a URL medicos
public class MedicoController {

    @Autowired //Ele instancia e passa o objeto repository dentro da classe Controller
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //converte uma lista de Medico para uma lista de DadosListagemMedico
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    // Exclusão física no banco de dados
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deletar(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    //exclusão lógica no banco de dados
    //{id} se trata de um parâmetro dinamico
    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
