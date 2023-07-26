package br.com.clima.application.domain.model.cie;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tb_cities")
@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ClimateCities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "state", nullable=false)
    private String state;

    @Column(name = "country", nullable=false)
    private String contry;

    @Column(name = "date", nullable=false)
    private String date;

    @Column(name = "temp_min")
    private Double tempmin;

    @Column(name = "temp_max")
    private Double tempmax;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_deg")
    private Double winddeg;

    @Column(name = "wind_gust")
    private Double windgust;
}
