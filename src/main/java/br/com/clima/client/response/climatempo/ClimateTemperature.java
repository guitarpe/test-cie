package br.com.clima.client.response.climatempo;

import lombok.Data;

@Data
public class ClimateTemperature {
    public Range last_year;
    public Range normal;
    public Range forecast;
}
