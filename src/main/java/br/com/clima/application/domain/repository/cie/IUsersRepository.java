package br.com.clima.application.domain.repository.cie;

import br.com.clima.application.domain.model.cie.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long>  {
	Users findByUsername(String username);
}
