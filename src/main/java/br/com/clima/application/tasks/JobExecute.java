package br.com.clima.application.tasks;

import br.com.clima.application.dto.JobRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobExecute {
    private final SOAPClientSAAJ soapClient;
    private Timer timer;

    public void start(JobRequest request) {
        timer = new Timer();

        timer.schedule(TaskJob.builder()
                .sistema(request.getSistema())
                .integracao(request.getIntegracao())
                .acao(request.getAcao()).build(), 0, request.getIntervalo());
    }

    public void stop(JobRequest request) {
        timer.cancel();
    }

    @Getter
    @Setter
    @Builder
    private class TaskJob extends TimerTask {

        private String sistema;
        private String integracao;
        private String acao;

        @Override
        public void run() {
            soapClient.callIntegration(sistema, integracao, acao);
        }
    }
}
