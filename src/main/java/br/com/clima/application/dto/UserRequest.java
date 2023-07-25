package br.com.clima.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {

    @JsonProperty(value="username", required = true)
    @NotNull(message="É necessário adicionar o nome do usuário")
    private String username;

    @JsonProperty(value="password", required = true)
    @NotNull(message="É necessário adicionar a senha do usuário")
    private String password;

    @JsonProperty(value="status", required = true)
    @NotNull(message="É necessário informar o status do usuário")
    private String status;

    @JsonProperty(value="perfil", required = true)
    @NotNull(message="É necessário informar o perfil do usuário")
    private String perfil;
}
