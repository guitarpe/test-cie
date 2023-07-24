package br.com.clima.application.domain.model.cie;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TB_USERS")
@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Users implements Serializable {

	public enum Role {USER, ADMIN}

	@Id
	@Column(name = "USER", nullable=false)
	private Long id;
	
	@Column(name = "LOGIN", nullable=false)
	private String username;
	
	@Column(name = "EMAIL", nullable=false)
	private String email;
	
	@Column(name = "PASSWORD", nullable=false)
	private String password;
	
	@Column(name = "SYSTEM", nullable=false)
	private String sistema;
	
	@Column(name = "STATUS", nullable=false)
	private String status;
	
	@Column(name = "ROLE", nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public Users(long id, String sistema) {
		this.id = id;
		this.sistema = sistema;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Users users = (Users) o;
		return id != null && Objects.equals(id, users.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
