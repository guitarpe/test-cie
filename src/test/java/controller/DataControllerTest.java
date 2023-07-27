package controller;

import br.com.clima.application.controller.DataController;
import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.domain.model.cie.Users;
import br.com.clima.application.service.data.response.DataResponse;
import br.com.clima.application.dto.CitiesDTO;
import br.com.clima.application.service.data.response.JobResponse;
import br.com.clima.application.service.data.request.UserRequest;
import br.com.clima.application.service.data.ClimateCitiesService;
import br.com.clima.application.service.data.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class DataControllerTest {

    @Mock
    private ClimateCitiesService climateCitiesService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DataController controller;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
    }

    @Test
    public void getInfoCitiesTest() throws Exception {
        CitiesDTO cities = new CitiesDTO();
        cities.setCidade("Maceió");
        cities.setUf("AL");
        cities.setDataInicio("2023-07-21");
        cities.setDataFinal("2023-07-26");

        String format = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        LocalDate dataIni = LocalDate.parse(cities.getDataInicio(), formatter);
        LocalDate dataFin = LocalDate.parse(cities.getDataFinal(), formatter);

        ClimateCities climateCities = ClimateCities.builder()
                                    .name("Maceió")
                                    .state("AL")
                                    .contry("BR")
                                    .date(dataIni)
                                    .tempmin(0.0)
                                    .tempmax(0.0)
                                    .windSpeed(0.0)
                                    .winddeg(0.0)
                                    .windgust(0.0)
                                    .windSpeed(0.0).build();

        DataResponse response = DataResponse.builder()
                .status(true)
                .mensagem("Sucesso")
                .data(Collections.singletonList(climateCities))
                .build();

        when(climateCitiesService.getCitiesInterval(
                cities.getCidade(), cities.getUf(),
                dataIni, dataFin))
                .thenReturn(response);

        ResponseEntity<JobResponse> result = controller.getInfoCities(cities);
        verify(climateCitiesService, times(1)).getCitiesInterval(
                cities.getCidade(), cities.getUf(),
                dataIni, dataFin);
        assertEquals(climateCities, Objects.requireNonNull(result.getBody()).getData());
    }

    @Test
    public void getUsersTest() throws Exception {
        List<Users> users = Arrays.asList(
                Users.builder().id(1L).username("user1").password("pass1").status('A').role(Users.Role.ADMIN).build(),
                Users.builder().id(2L).username("user2").password("pass2").status('A').role(Users.Role.USER).build(),
                Users.builder().id(3L).username("user3").password("pass3").status('A').role(Users.Role.USER).build()
        );

        when(userService.getAllUsers()).thenReturn((DataResponse) users);
        ResponseEntity<JobResponse> result = controller.getUsers();
        verify(userService, times(1)).getAllUsers();
        assertEquals(users, Objects.requireNonNull(result.getBody()).getData());
    }

    @Test
    public void saveUserTest() throws Exception {

        UserRequest user = new UserRequest();
        user.setUsername("user4");
        user.setPassword("pass4");
        user.setStatus('A');
        user.setPerfil("ADMIN");

        Users userReturn = Users.builder().id(4L)
                .username("user4").password("pass4").status('A').role(Users.Role.ADMIN).build();

        DataResponse result = userService.saveUser(user);
        verify(userService).saveUser(user);
        assertTrue(Objects.nonNull(result));
    }
}
