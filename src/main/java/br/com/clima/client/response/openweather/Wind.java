package br.com.clima.client.response.openweather;

import lombok.Data;

@Data
public class Wind {
    public double speed;
    public double deg;
    public double gust;
}
