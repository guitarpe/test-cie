package br.com.clima.application.controller;

import br.com.clima.application.service.data.response.DataResponse;
import br.com.clima.application.dto.CitiesDTO;
import br.com.clima.application.service.data.response.JobResponse;
import br.com.clima.application.service.data.request.UserRequest;
import br.com.clima.application.enuns.Messages;
import br.com.clima.application.service.data.ClimateCitiesService;
import br.com.clima.application.service.data.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

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
    public ResponseEntity<JobResponse> getInfoCities(@Valid @RequestBody CitiesDTO request) throws Exception {

        DataResponse response = climateCitiesService.getCitiesInterval(
                request.getCidade(),
                request.getUf(),
                new Date(request.getDataInicio()),
                new Date(request.getDataFinal())
        );

        if(!response.isStatus()) {
            throw new EntityNotFoundException(response.getMensagem());
        }

        return ResponseEntity.ok().body(JobResponse.builder()
                        .code(200)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .data(response.getData())
                        .message(Messages.MSG_SUCCESS.value()).build());
    }

    @GetMapping(value="/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobResponse> getUsers() throws Exception {

        DataResponse response =  userService.getAllUsers();

        if(!response.isStatus()) {
            throw new EntityNotFoundException(response.getMensagem());
        }

        return ResponseEntity.ok().body(JobResponse.builder()
                        .code(200)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .data(response.getData())
                        .message(Messages.MSG_SUCCESS.value()).build());
    }

    @PostMapping(value="/user/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobResponse> saveUser(@Valid @RequestBody UserRequest request) throws Exception {

        DataResponse response = userService.saveUser(request);

        if(!response.isStatus()) {
            throw new EntityNotFoundException(response.getMensagem());
        }

        return ResponseEntity.ok().body(JobResponse.builder()
                        .code(200)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .data(response.getData())
                        .message(Messages.MSG_SUCCESS.value()).build());
    }
}
