package br.com.clima.application.domain.repository.cie;

import br.com.clima.application.domain.model.cie.ClimateCities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IClimateCitiesRepository extends JpaRepository<ClimateCities, Long> {

    List<ClimateCities> findClimateCitiesByNameAndAndStateAndDateBetween(
            String name, String uf, LocalDate dtIni, LocalDate dtFim);
}
