package br.com.clima.client;

import br.com.clima.client.response.OWGeoResponse;
import br.com.clima.client.response.OWResponse;
import feign.Headers;
import feign.RequestLine;

public interface APIOpenWeather {
    @RequestLine("GET /weather?lat={lat}&lon={lon}&appid={token}")
    @Headers({"Content-Type: application/json"})
    OWResponse getWheatherCity();

    @RequestLine("GET /direct?q={city},{uf},55&limit=1&appid={token}")
    @Headers({"Content-Type: application/json"})
    OWGeoResponse getGeolocationCity();
}
