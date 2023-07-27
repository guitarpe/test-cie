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
    @GetMapping("/data/2.5/weather")
    @Headers({"Content-Type: application/json"})
    OWResponse getWeatherCity(@RequestParam("lat") String lat, @RequestParam("lon") String lon, @RequestParam("appid") String token);

    @GetMapping("/geo/1.0/direct")
    @Headers({"Content-Type: application/json"})
    List<OWGeoResponse> getGeolocationCity(@RequestParam("q") String city, @RequestParam("limit") String limit, @RequestParam("appid") String appid);
}
