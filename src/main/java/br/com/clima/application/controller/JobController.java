package br.com.clima.application.controller;

import br.com.clima.application.service.data.response.DataResponse;
import br.com.clima.application.service.data.request.JobRequest;
import br.com.clima.application.service.data.response.JobResponse;
import br.com.clima.application.tasks.CallTasks;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<JobResponse> startJob(@Valid @RequestBody JobRequest request) throws Exception {

        DataResponse response = callTasks.startTaskOption(request);

        if(!response.isStatus()) {
            throw new EntityNotFoundException(response.getMensagem());
        }

        return ResponseEntity.ok().body(JobResponse.builder()
                .code(200)
                .success(true)
                .timestamp(LocalDateTime.now())
                .message(response.getMensagem()).build());
    }

    @PostMapping(value="/stop",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobResponse> stopJob(@Valid @RequestBody JobRequest request) throws Exception {

        DataResponse response = callTasks.stopTaskOption(request);

        if(!response.isStatus()) {
            throw new EntityNotFoundException(response.getMensagem());
        }

        return ResponseEntity.ok().body(JobResponse.builder()
                .code(200)
                .success(true)
                .timestamp(LocalDateTime.now())
                .message(response.getMensagem()).build());
    }
}
