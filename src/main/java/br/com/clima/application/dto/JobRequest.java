package br.com.clima.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JobRequest {
    @JsonProperty(value="integracao", required = true)
    @NotNull(message="É necessário adicionar o nome da integração relacionada ao serviço")
    private String integracao;

    @JsonProperty(value="cidade", required = true)
    @NotNull(message="É necessário adicionar código da cidade que deseja consultar")
    private String cidade;

    @JsonProperty(value="intervalo", required = true)
    @NotNull(message="É necessário informar o intervalo de execução")
    private Long intervalo;
}
