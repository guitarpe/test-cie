package br.com.clima.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@AllArgsConstructor
public class CTRequest {
    @JsonProperty("USERNAME")
    private String username;

    @JsonProperty("CD_ARQUIVO")
    private String cdArquivo;
}
