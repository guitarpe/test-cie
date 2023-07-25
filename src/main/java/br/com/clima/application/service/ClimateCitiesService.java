package br.com.clima.application.service;

import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.domain.repository.cie.IClimateCitiesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClimateCitiesService {

    IClimateCitiesRepository repository;

    public List<ClimateCities> getCitiesInterval(String name, String uf, Date dtIni, Date dtFim){
        return repository.findClimateCitiesByNameAndAndStateAndDateBetween(name, uf, dtIni, dtFim);
    }

    public void saveTemperatureCity(ClimateCities climate){
        repository.save(climate);
    }
}
