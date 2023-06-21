package br.dev.carolbastos.api.controller;

import br.dev.carolbastos.api.domain.medico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

//@Valid: Vem do Bean Validation e faz validacoes dos atributos de um Record
@RestController
@RequestMapping("medicos") //mapeia para a URL medicos
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired //Ele instancia e passa o objeto repository dentro da classe Controller
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uribuilder){
       var medico = new Medico(dados);
       repository.save(medico);

       var uri = uribuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

       return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //converte uma lista de Medico para uma lista de DadosListagemMedico
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return  ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    // Exclusão física no banco de dados
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deletar(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    //exclusão lógica no banco de dados
    //{id} se trata de um parâmetro dinamico
    //no verbo http DELETE sempre  usar o codigo de status 204 - noContent
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        //converte uma lista de Medico para uma lista de DadosListagemMedico
        var medico = repository.getReferenceById(id);
        return  ResponseEntity.ok(new DadosDetalhamentoMedico(medico));


    }
}
