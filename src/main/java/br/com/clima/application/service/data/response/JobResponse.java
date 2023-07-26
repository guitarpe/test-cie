package br.com.clima.application.service.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private long code;
    private boolean success;
	private LocalDateTime timestamp;
    private String message;
    private Object data;
}
