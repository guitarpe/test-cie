package br.com.clima.application.domain.model.cie;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Users implements Serializable {

	public enum Role {USER, ADMIN}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_users_id_seq")
	@SequenceGenerator(name = "tb_users_id_seq", sequenceName = "tb_users_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "login", nullable=false)
	private String username;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	@Column(name = "status", nullable=false)
	private char status;
	
	@Column(name = "role", nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public Users(long id, String sistema) {
		this.id = id;
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
