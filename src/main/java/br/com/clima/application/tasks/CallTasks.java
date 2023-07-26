package br.com.clima.application.tasks;

import br.com.clima.application.service.data.response.DataResponse;
import br.com.clima.application.service.data.request.JobRequest;
import br.com.clima.application.enuns.Messages;

import br.com.clima.application.service.jobs.OpenWeatherCallService;
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

    public DataResponse startTaskOption(JobRequest request) throws Exception {
        try {
            verifyInfoRequest(request);

            service.start(request.getIntervalo(), request.getCidade(), request.getUf());

            return DataResponse.builder()
                    .status(true)
                    .mensagem(Messages.MSG_INI_SUCCESS.value())
                    .data(null).build();

        }catch (Exception ex){
            throw new Exception(Messages.MSG_ERROR_START_CALL.value()+": "+ex.getMessage());
        }
    }

    private void verifyInfoRequest(JobRequest request) throws Exception {
        if (Objects.isNull(request.getCidade())) {
            throw new Exception(Messages.MSG_CIDADE_NOT_FOUND.value());
        }

        if (Objects.isNull(request.getUf())) {
            throw new Exception(Messages.MSG_UF_NOT_FOUND.value());
        }

        if (Objects.isNull(request.getIntervalo())) {
            throw new Exception(Messages.MSG_INTERVAL_NOT_FOUND.value());
        }
    }

    public DataResponse stopTaskOption(JobRequest request) throws Exception {
        try{
            verifyInfoRequest(request);

            service.stop();

            return DataResponse.builder()
                    .status(true)
                    .mensagem(Messages.MSG_FIN_SUCCESS.value())
                    .data(null).build();

        }catch (Exception ex){
            throw new Exception(Messages.MSG_ERROR_START_CALL.value()+": "+ex.getMessage());
        }
    }
}
