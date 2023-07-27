package br.com.clima.application.domain.model.cie;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="tb_cities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ClimateCities {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_cities_id_seq")
    @SequenceGenerator(name = "tb_cities_id_seq", sequenceName = "tb_cities_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "state", nullable=false)
    private String state;

    @Column(name = "country", nullable=false)
    private String contry;

    @Column(name = "date", nullable=false)
    private LocalDate date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClimateCities that = (ClimateCities) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
