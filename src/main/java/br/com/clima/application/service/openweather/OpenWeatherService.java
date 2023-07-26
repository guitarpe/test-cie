package br.com.clima.application.service.openweather;

import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.exceptions.CustomErrorDecoder;
import br.com.clima.application.service.ClimateCitiesService;
import br.com.clima.client.APIOpenWeather;
import br.com.clima.client.response.OWGeoResponse;
import br.com.clima.client.response.OWResponse;
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

    ClimateCitiesService service;

    private APIOpenWeather getAPIClient(long lat, long lon){
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new CustomErrorDecoder())
                .requestInterceptor(template -> {
                    template.query("lat", String.valueOf(lat));
                    template.query("lon", String.valueOf(lon));
                    template.query("token", token);
                })
                .target(APIOpenWeather.class, endpoint);
    }

    private OWGeoResponse getGeolocationClient(String city, String uf) throws Exception{
        APIOpenWeather client = Feign.builder()
                                    .encoder(new GsonEncoder())
                                    .decoder(new GsonDecoder())
                                    .errorDecoder(new CustomErrorDecoder())
                                    .requestInterceptor(template -> {
                                        template.query("city", city);
                                        template.query("uf", uf);
                                        template.query("token", token);
                                    })
                                    .target(APIOpenWeather.class, endpoint);

        return client.getGeolocationCity();
    }

    private void printLog(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        log.info(json);
    }

    public void climateRegister(String city, String uf) throws Exception {

        OWGeoResponse geolocation = getGeolocationClient(city, uf);

        APIOpenWeather client = getAPIClient(geolocation.lat, geolocation.lon);
        OWResponse response = client.getWheatherCity();

        String format = "dd/MM/yyyy";
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

        printLog(response);
    }
}
