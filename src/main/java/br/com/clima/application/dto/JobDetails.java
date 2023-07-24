package br.com.clima.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDetails {
    private long code;
    private boolean sucesso;
	private LocalDateTime timestamp;
    private String message;
    private Objects data;
}
