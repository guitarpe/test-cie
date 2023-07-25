package br.com.clima.application.service;

import br.com.clima.application.domain.model.cie.Users;
import br.com.clima.application.domain.repository.cie.IUsersRepository;
import br.com.clima.application.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    IUsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users saveUser(UserRequest request){
        Users user = Users.builder()
                            .username(request.getUsername())
                            .password(request.getPassword())
                            .status(request.getStatus())
                            .role(Users.Role.valueOf(request.getPerfil())).build();

        if(Objects.isNull(usersRepository.findByUsername(request.getUsername())))
            usersRepository.save(user);

        return user;
    }
}
