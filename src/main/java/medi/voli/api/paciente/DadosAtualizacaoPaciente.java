package medi.voli.api.paciente;

import medi.voli.api.endereco.Endereco;

public record DadosAtualizacaoPaciente(
        String nome,
        String telefone,
        Endereco endereco) {
}
