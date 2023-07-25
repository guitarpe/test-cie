package br.com.clima.application.service;

import br.com.clima.application.service.openweather.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpenWeatherCallService {
    OpenWeatherService service;

    private Timer timer;

    public void start(long interval, String city, String uf) {
        timer = new Timer();
        timer.schedule(new Task(city, uf), 0, interval);
    }

    public void stop() {
        timer.cancel();
    }

    private class Task extends TimerTask {
        private String city;
        private String uf;

        public Task(String city, String uf) {
            this.city = city;
            this.uf = uf;
        }

        @Override
        public void run() {service.climateRegister(city, uf);}
    }
}