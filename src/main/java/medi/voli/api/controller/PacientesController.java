package medi.voli.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voli.api.medico.DadosListagemMedico;
import medi.voli.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPacientes(@RequestBody @Valid DadosCadastroPacientes dados){

        repository.save(new Paciente(dados));
    }
    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size =10, sort = {"nome"})Pageable paginacao){

      return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }
    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPaciente dados ){

           Paciente paciente = repository.getReferenceById(id);
           paciente.atualizarInformacoes(dados);
     }
     @PostMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){

        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}


