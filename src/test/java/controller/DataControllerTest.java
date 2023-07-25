package controller;

import br.com.clima.application.controller.DataController;
import br.com.clima.application.domain.model.cie.ClimateCities;
import br.com.clima.application.domain.model.cie.Users;
import br.com.clima.application.dto.CitiesRequest;
import br.com.clima.application.dto.JobDetails;
import br.com.clima.application.dto.UserRequest;
import br.com.clima.application.service.ClimateCitiesService;
import br.com.clima.application.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
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
        CitiesRequest cities = new CitiesRequest();
        cities.setCidade("Maceió");
        cities.setUf("AL");
        cities.setDataInicio("2023-07-21");
        cities.setDataFinal("2023-07-26");

        ClimateCities climateCities = ClimateCities.builder()
                                    .name("Maceió")
                                    .state("AL")
                                    .contry("BR")
                                    .temperature("0")
                                    .date("2023-07-07")
                                    .windSpeed(null)
                                    .hdInsert(LocalDateTime.now()).build();

        when(climateCitiesService.getCitiesInterval(
                cities.getCidade(), cities.getUf(),
                new Date(cities.getDataInicio()), new Date(cities.getDataFinal())))
                .thenReturn(Collections.singletonList(climateCities));

        ResponseEntity<JobDetails> result = controller.getInfoCities(cities);
        verify(climateCitiesService, times(1)).getCitiesInterval(
                cities.getCidade(), cities.getUf(),
                new Date(cities.getDataInicio()), new Date(cities.getDataFinal()));
        assertEquals(climateCities, Objects.requireNonNull(result.getBody()).getData());
    }

    @Test
    public void getUsersTest() throws Exception {
        List<Users> users = Arrays.asList(
                Users.builder().id(1L).username("user1").password("pass1").status("A").role(Users.Role.ADMIN).build(),
                Users.builder().id(2L).username("user2").password("pass2").status("A").role(Users.Role.USER).build(),
                Users.builder().id(3L).username("user3").password("pass3").status("A").role(Users.Role.USER).build()
        );

        when(userService.getAllUsers()).thenReturn(users);
        ResponseEntity<JobDetails> result = controller.getUsers();
        verify(userService, times(1)).getAllUsers();
        assertEquals(users, Objects.requireNonNull(result.getBody()).getData());
    }

    @Test
    public void saveUserTest(){

        UserRequest user = new UserRequest();
        user.setUsername("user4");
        user.setPassword("pass4");
        user.setStatus("A");
        user.setPerfil("ADMIN");

        Users userReturn = Users.builder().id(4L)
                .username("user4").password("pass4").status("A").role(Users.Role.ADMIN).build();

        when(userService.saveUser(user)).thenReturn(userReturn);
        Users result = userService.saveUser(user);
        verify(userService).saveUser(user);
        assertTrue(Objects.nonNull(result));
    }
}
