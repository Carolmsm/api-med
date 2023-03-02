package medi.voli.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medi.voli.api.endereco.Endereco;
import medi.voli.api.paciente.Paciente;

import java.util.List;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    private  String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    //@OneToMany
   // @JoinColumn(name = "paciente_id", nullable = false)
   // private List<Paciente> paciente;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.ativo = true;
        this.endereco = new Endereco(dados.endereco());
    }
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
            if (dados.nome() != null) {
                this.nome = dados.nome();
            }
            if (dados.telefone() != null) {
                this.telefone = dados.telefone();
            }
            if (dados.endereco() != null) {
                this.endereco.atualizarInformacoes(dados.endereco());
            }

        }


    public void excluir() {
        this.ativo = false;
    }
}
