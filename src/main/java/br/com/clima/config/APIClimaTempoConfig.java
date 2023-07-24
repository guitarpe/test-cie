package br.com.clima.config;

import br.com.clima.client.APIClimaTempoClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;

public class APIClimaTempoConfig {

    private static String tokenApi;

    @Value("${api-climatempo.token}")
    public void setTokenApi(String token){
        this.tokenApi = token;
    }

    public static APIClimaTempoClient createLocale(String baseUrl, String locale) {

        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(new RequestInterceptor() {
                    @Override
                    public void apply(RequestTemplate template) {
                        template.query("locale", locale);
                        template.query("token", tokenApi);
                    }
                })
                .target(APIClimaTempoClient.class, baseUrl);
    }
}
