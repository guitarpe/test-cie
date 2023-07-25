package br.com.clima.application.controller;

import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.domain.model.cie.Users;
import br.com.clima.application.dto.CitiesRequest;
import br.com.clima.application.dto.JobDetails;
import br.com.clima.application.dto.UserRequest;
import br.com.clima.application.enuns.Messages;
import br.com.clima.application.service.ClimateCitiesService;
import br.com.clima.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/api-climate/data/controls")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataController {

    ClimateCitiesService climateCitiesService;
    UserService userService;

    @PostMapping(value="/cities",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> getInfoCities(@Valid @RequestBody CitiesRequest request) throws Exception {

        List<ClimateCities> list = climateCitiesService.getCitiesInterval(
                request.getCidade(),
                request.getUf(),
                new Date(request.getDataInicio()),
                new Date(request.getDataFinal())
        );

        return ResponseEntity.ok().body(JobDetails.builder()
                        .code(200)
                        .sucesso(true)
                        .timestamp(LocalDateTime.now())
                        .data(list)
                        .message(Messages.MSG_INI_SUCCESS.value()).build());
    }

    @GetMapping(value="/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> getUsers() throws Exception {

        List<Users> list = userService.getAllUsers();

        return ResponseEntity.ok().body(JobDetails.builder()
                        .code(200)
                        .sucesso(true)
                        .timestamp(LocalDateTime.now())
                        .data(list)
                        .message(Messages.MSG_INI_SUCCESS.value()).build());
    }

    @PostMapping(value="/user/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> saveUser(@Valid @RequestBody UserRequest request) throws Exception {

        Users user = userService.saveUser(request);

        return ResponseEntity.ok().body(JobDetails.builder()
                        .code(200)
                        .sucesso(true)
                        .timestamp(LocalDateTime.now())
                        .data(user)
                        .message(Messages.MSG_INI_SUCCESS.value()).build());
    }
}
