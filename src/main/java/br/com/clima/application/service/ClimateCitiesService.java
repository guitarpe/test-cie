package br.com.clima.application.service;

import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.domain.repository.cie.IClimateCitiesRepository;
import br.com.clima.application.dto.CallResponse;
import br.com.clima.application.enuns.Messages;
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

    public CallResponse getCitiesInterval(String name, String uf, Date dtIni, Date dtFim) throws Exception{
        try{
            List<ClimateCities> list = repository.findClimateCitiesByNameAndAndStateAndDateBetween(name, uf, dtIni, dtFim);

            return CallResponse.builder()
                    .status(true)
                    .mensagem(Messages.MSG_SUCCESS.value())
                    .data(list).build();
        }catch (Exception ex){
            throw new Exception(Messages.MSG_ERROR_LIST_CITIES.value()+": "+ex.getMessage());
        }
    }

    public CallResponse saveTemperatureCity(ClimateCities climate) throws Exception{
        try{
            repository.save(climate);

            return CallResponse.builder()
                    .status(true)
                    .mensagem(Messages.MSG_SAVE_SUCCESS.value())
                    .data(null).build();
        }catch (Exception ex){
            throw new Exception(Messages.MSG_ERROR_SAVE_CITIES.value()+": "+ex.getMessage());
        }
    }
}
