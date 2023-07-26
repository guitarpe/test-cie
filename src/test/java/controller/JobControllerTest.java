package controller;

import br.com.clima.application.controller.JobController;
import br.com.clima.application.dto.CallResponse;
import br.com.clima.application.dto.JobDetails;
import br.com.clima.application.dto.JobRequest;
import br.com.clima.application.tasks.CallTasks;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JobControllerTest {
    @InjectMocks
    private JobController jobController;

    @Mock
    private CallTasks callTasks;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStartJob_Success() throws Exception {
        JobRequest request = new JobRequest();
        CallResponse response = CallResponse.builder().build();
        response.setStatus(true);
        response.setMensagem("Task started successfully");

        when(callTasks.startTaskOption(request)).thenReturn(response);

        ResponseEntity<JobDetails> result = jobController.startJob(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(200, Objects.requireNonNull(result.getBody()).getCode());
        assertTrue(result.getBody().isSuccess());
        assertEquals(response.getMensagem(), result.getBody().getMessage());

        verify(callTasks, times(1)).startTaskOption(request);
    }

    @Test
    public void testStartJob_Failure() throws Exception {
        JobRequest request = new JobRequest();
        CallResponse response = CallResponse.builder().build();
        response.setStatus(false);
        response.setMensagem("Failed to start task");

        when(callTasks.startTaskOption(request)).thenReturn(response);

        try {
            jobController.startJob(request);
        } catch (EntityNotFoundException e) {
            assertEquals(response.getMensagem(), e.getMessage());
        }

        verify(callTasks, times(1)).startTaskOption(request);
    }
}
