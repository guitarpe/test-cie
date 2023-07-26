package br.com.clima.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CallResponse {
    private boolean status;
    private String mensagem;
    private Object data;
}
