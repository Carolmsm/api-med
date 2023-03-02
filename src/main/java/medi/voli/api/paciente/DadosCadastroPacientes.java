package medi.voli.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medi.voli.api.endereco.DadosEndereco;
import medi.voli.api.endereco.Endereco;
import medi.voli.api.medico.DadosCadastroMedico;

public record DadosCadastroPacientes(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotNull
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull @Valid
        DadosEndereco endereco) {


}
