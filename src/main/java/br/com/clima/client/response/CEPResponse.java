package br.com.clima.client.response;

import lombok.Data;

@Data
public class CEPResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
