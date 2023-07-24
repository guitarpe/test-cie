package br.com.clima.client;

import br.com.clima.client.response.CEPResponse;
import feign.Param;
import feign.RequestLine;

public interface APIViaCep {
    @RequestLine("GET /ws/{cep}/json/")
    CEPResponse getCepInfo(@Param("cep") String cep);
}
