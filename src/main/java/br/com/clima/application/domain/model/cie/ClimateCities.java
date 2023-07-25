package br.com.clima.application.domain.model.cie;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TB_CITIES")
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

    @Column(name = "TEMP_MIN")
    private Double tempmin;

    @Column(name = "TEMP_MAX")
    private Double tempmax;

    @Column(name = "WIND_SPEED")
    private Double windSpeed;

    @Column(name = "WIND_DEG")
    private Double winddeg;

    @Column(name = "WIND_GUST")
    private Double windgust;
}
