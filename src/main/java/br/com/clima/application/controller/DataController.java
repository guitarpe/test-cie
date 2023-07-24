package br.com.clima.application.controller;

import br.com.clima.application.dto.JobDetails;
import br.com.clima.application.dto.JobRequest;
import br.com.clima.application.enuns.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/rest/api-climate/data/controls")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataController {

    @GetMapping(value="/ibge/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> getInfoIBGE(@Valid @PathVariable("cep") String cep) throws Exception {

        return ResponseEntity.ok().body(JobDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(Messages.MSG_INI_SUCCESS.value()).build());
    }

    @PostMapping(value="/cities",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> startJob(@Valid @RequestBody JobRequest request) throws Exception {

        return ResponseEntity.ok().body(JobDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(Messages.MSG_INI_SUCCESS.value()).build());
    }

    @PostMapping(value="/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> getUsers(@Valid @RequestBody JobRequest request) throws Exception {

        return ResponseEntity.ok().body(JobDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(Messages.MSG_INI_SUCCESS.value()).build());
    }

    @PostMapping(value="/user/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> saveUser(@Valid @RequestBody JobRequest request) throws Exception {

        return ResponseEntity.ok().body(JobDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(Messages.MSG_INI_SUCCESS.value()).build());
    }
}
