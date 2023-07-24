package br.com.clima.application.tasks;

import br.com.clima.application.dto.JobRequest;
import br.com.clima.application.enuns.Integrations;
import br.com.clima.application.enuns.Messages;
import br.com.clima.application.service.ClimaTempoRainCallService;

import br.com.clima.application.service.ClimaTempoTempCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CallTasks {

    private final JobExecute jobExecute;
    private final ClimaTempoRainCallService ctRainCallService;
    private final ClimaTempoTempCallService ctTempCallService;

    public void startTaskOption(JobRequest request) throws Exception {

        jobExecute.start(request);

        if(Objects.isNull(Integrations.fromValue(request.getIntegracao()))){
            throw new Exception(Messages.MSG_NOT_FOUND.value());
        }

        if(Objects.isNull(request.getCidade())){
            throw new Exception(Messages.MSG_CITY_NOT_FOUND.value());
        }

        switch (Objects.requireNonNull(Integrations.fromValue(request.getIntegracao()))) {
            case INT_RAIN:
                ctRainCallService.start(request.getIntervalo());
                break;
            case INT_TEMPERATURE:
                ctTempCallService.start(request.getIntervalo());
                break;
            default:
                break;
        }

        log.info("Job CLIMATEMPO - " + request.getIntegracao() + " Iniciado");
    }

    public void stopTaskOption(JobRequest request) throws Exception {

        jobExecute.stop(request);

        if(Objects.isNull(Integrations.fromValue(request.getIntegracao()))){
            throw new Exception(Messages.MSG_NOT_FOUND.value());
        }

        if(Objects.isNull(request.getCidade())){
            throw new Exception(Messages.MSG_CITY_NOT_FOUND.value());
        }

        switch (Objects.requireNonNull(Integrations.fromValue(request.getIntegracao()))) {

            case INT_RAIN:
                ctRainCallService.stop();
                break;
            case INT_TEMPERATURE:
                ctTempCallService.stop();
                break;
            default:
                break;
        }

        log.info("Job CLIMATEMPO -" + request.getIntegracao() + " Finalizado");
    }
}
