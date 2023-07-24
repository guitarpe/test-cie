package br.com.clima.client.response;

import br.com.clima.client.response.climatempo.Datum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CTResponse {
    private int id;
    private String name;
    private String state;
    private String country;
    public ArrayList<Datum> data;
}
