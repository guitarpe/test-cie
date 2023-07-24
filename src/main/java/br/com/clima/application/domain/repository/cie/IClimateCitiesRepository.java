package br.com.clima.application.domain.repository.cie;

import br.com.clima.application.domain.model.cie.ClimateCities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClimateCitiesRepository extends JpaRepository<ClimateCities, Long> {
}
