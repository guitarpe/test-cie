package br.com.clima.application.service.jobs.openweather;

import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.exceptions.CustomErrorDecoder;
import br.com.clima.application.service.data.ClimateCitiesService;
import br.com.clima.client.APIOpenWeather;
import br.com.clima.client.response.OWGeoResponse;
import br.com.clima.client.response.OWResponse;
import br.com.clima.util.Utils;
import com.google.gson.Gson;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpenWeatherService {

    @Value("${openweathermap.token}")
    public String token;

    @Value("${openweathermap.endpoint}")
    public String endpoint;

    @Value("${openweathermap.endpoint-geo}")
    public String endpointgeo;

    private final ClimateCitiesService service;

    private final APIOpenWeather client;

    private OWGeoResponse getGeolocationClient(String city, String uf) throws Exception{
        try {
            String cityParams = Utils.removeAccentuation(city) + "," + uf + ",55";
            String limit = "1";

            return client.getGeolocationCity(cityParams, limit, token).get(0);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    private void printLog(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        log.info(json);
    }

    public void climateRegister(String city, String uf) throws Exception {

        OWGeoResponse geolocation = getGeolocationClient(city, uf);
        OWResponse response = client.getWeatherCity(geolocation.lat, geolocation.lon, token);

        String format = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String dateFormatNow = LocalDateTime.now().format(formatter);

        ClimateCities climate = ClimateCities.builder()
                                    .name(geolocation.getName())
                                    .state(geolocation.getState())
                                    .contry(geolocation.getCountry())
                                    .date(dateFormatNow)
                                    .tempmin(response.getMain().getTemp_min())
                                    .tempmax(response.getMain().getTemp_max())
                                    .windSpeed(response.getWind().getSpeed())
                                    .winddeg(response.getWind().getDeg())
                                    .windgust(response.getWind().getGust())
                                    .build();

        service.saveTemperatureCity(climate);

        printLog(climate);
    }
}
