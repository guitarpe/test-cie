package br.com.clima.application.tasks;

import br.com.clima.application.dto.JobRequest;
import br.com.clima.application.enuns.Messages;

import br.com.clima.application.service.OpenWeatherCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CallTasks {

    private final OpenWeatherCallService service;

    public void startTaskOption(JobRequest request) throws Exception {

        if(Objects.isNull(request.getCidade())){
            throw new Exception(Messages.MSG_CIDADE_NOT_FOUND.value());
        }

        if(Objects.isNull(request.getUf())){
            throw new Exception(Messages.MSG_UF_NOT_FOUND.value());
        }

        if(Objects.isNull(request.getIntervalo())){
            throw new Exception(Messages.MSG_INTERVAL_NOT_FOUND.value());
        }

        service.start(request.getIntervalo(), request.getCidade(), request.getUf());

        log.info(Messages.MSG_INI_SUCCESS.value());
    }

    public void stopTaskOption(JobRequest request) throws Exception {

        if(Objects.isNull(request.getCidade())){
            throw new Exception(Messages.MSG_CIDADE_NOT_FOUND.value());
        }

        if(Objects.isNull(request.getUf())){
            throw new Exception(Messages.MSG_UF_NOT_FOUND.value());
        }

        if(Objects.isNull(request.getIntervalo())){
            throw new Exception(Messages.MSG_INTERVAL_NOT_FOUND.value());
        }

        service.stop();

        log.info(Messages.MSG_FIN_SUCCESS.value());
    }
}
