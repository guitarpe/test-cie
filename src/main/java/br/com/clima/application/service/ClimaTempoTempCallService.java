package br.com.clima.application.service;

import br.com.clima.application.service.climatempo.ClimaTempoRainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClimaTempoTempCallService {

    ClimaTempoRainService service;

    private Timer timer;

    public void start(long interval) {
        timer = new Timer();
        timer.schedule(new Task(), 0, interval);
    }

    public void stop() {
        timer.cancel();
    }

    private class Task extends TimerTask {
        @Override
        public void run() {service.registrarTemperatura();}
    }
}
