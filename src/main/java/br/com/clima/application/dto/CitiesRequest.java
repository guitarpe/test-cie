package br.com.clima.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CitiesRequest {
    @JsonProperty(value="cidade", required = true)
    @NotNull(message="É necessário informar a cidade")
    private String cidade;

    @JsonProperty(value="uf", required = true)
    @NotNull(message="É necessário informar a UF")
    private String uf;

    @JsonProperty(value="dataInicio", required = true)
    @NotNull(message="É necessário informar a data de início")
    private String dataInicio;

    @JsonProperty(value="dataFinal", required = true)
    @NotNull(message="É necessário informar a data final")
    private String dataFinal;
}
