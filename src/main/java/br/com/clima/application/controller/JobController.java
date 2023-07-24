package br.com.clima.application.controller;

import br.com.clima.application.dto.JobRequest;
import br.com.clima.application.dto.JobDetails;
import br.com.clima.application.enuns.Messages;
import br.com.clima.application.tasks.CallTasks;
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
@RequestMapping("/rest/api-climate/jobs/controls")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobController {

    private final CallTasks callTasks;

    @PostMapping(value="/start",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> startJob(@Valid @RequestBody JobRequest request) throws Exception {

        callTasks.startTaskOption(request);

        return ResponseEntity.ok().body(JobDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(Messages.MSG_INI_SUCCESS.value()).build());
    }

    @PostMapping(value="/stop",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobDetails> stopJob(@Valid @RequestBody JobRequest request) throws Exception {

        callTasks.stopTaskOption(request);

        return ResponseEntity.ok().body(JobDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(Messages.MSG_FIN_SUCCESS.value()).build());
    }
}
