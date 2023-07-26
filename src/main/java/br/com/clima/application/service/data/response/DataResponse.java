package br.com.clima.application.service.data.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataResponse {
    private boolean status;
    private String mensagem;
    private Object data;
}
