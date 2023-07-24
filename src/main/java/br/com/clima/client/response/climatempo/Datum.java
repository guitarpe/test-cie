package br.com.clima.client.response.climatempo;

import lombok.Data;

@Data
public class Datum {
    public String date;
    public ClimateTemperature climate_temperature;
}
