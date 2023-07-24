package br.com.clima.application.domain.model.cie;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TB_CITIES", schema="dbo")
@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ClimateCities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CD_ALUNO", nullable=false)
    private String aluno;

    @Column(name = "CD_GUID", nullable=false)
    private String guid;

    @Column(name = "DS_SERVICO", nullable=false)
    private String servico;

    @Column(name = "DS_ACAO", nullable=false)
    private String acao;

    @Column(name = "DT_DIA", nullable=false)
    private String dia;

    @Column(name = "NUM_HORA", nullable=false)
    private Integer hora;

    @Column(name = "DH_INSERT", nullable=false)
    private LocalDateTime dtInsert;
}
