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

    @Column(name = "NAME", nullable=false)
    private String name;

    @Column(name = "STATE", nullable=false)
    private String state;

    @Column(name = "COUNTRY", nullable=false)
    private String contry;

    @Column(name = "DATE", nullable=false)
    private String date;

    @Column(name = "TEMPERATURE", nullable=false)
    private String temperature;

    @Column(name = "WIND_SPEED", nullable=false)
    private Integer windSpeed;

    @Column(name = "DH_INSERT", nullable=false)
    private LocalDateTime hdInsert;

    @Column(name = "DH_UPDATE", nullable=false)
    private LocalDateTime dhUpdate;
}
