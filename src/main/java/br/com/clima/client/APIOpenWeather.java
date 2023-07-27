package br.com.clima.client;

import br.com.clima.client.response.OWGeoResponse;
import br.com.clima.client.response.OWResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "openWeatherClient", name = "openWeatherClient", url = "${openweathermap.endpoint}")
public interface APIOpenWeather {
    @GetMapping(name="/data/2.5/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({"Content-Type: application/json"})
    OWResponse getWeatherCity(@RequestParam("lat") Long lat, @RequestParam("lon") Long lon, @RequestParam("token") String token);

    @GetMapping(name="/geo/1.0/direct", produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({"Content-Type: application/json"})
    List<OWGeoResponse> getGeolocationCity(@RequestParam("q") String city, @RequestParam("limit") String limit, @RequestParam("appid") String appid);
}
