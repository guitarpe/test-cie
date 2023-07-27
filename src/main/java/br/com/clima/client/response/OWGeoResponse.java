package br.com.clima.client.response;

import lombok.Data;

@Data
public class OWGeoResponse {
    public String name;
    public String lat;
    public String lon;
    public String country;
    public String state;
}
