package medi.voli.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voli.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dados){

        repository.save(new Medico(dados));
    }
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
       Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {

         var medico = repository.getReferenceById(id);

         medico.excluir();


    }

}
