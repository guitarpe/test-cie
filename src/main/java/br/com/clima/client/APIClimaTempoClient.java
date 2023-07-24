package br.com.clima.client;

import br.com.clima.client.response.CTResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface APIClimaTempoClient {

    @RequestLine("GET /locale/{locale}?token={token}")
    @Headers({"Content-Type: application/json"})
    CTResponse getClimateCity(@Param("locale") String locale);
}
