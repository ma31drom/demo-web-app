package by.pvt.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "USER_TABLE", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String login;
	private String password;

	private String email;

	private String filePath;
	private String fileName;
	private String mimeType;

	private boolean active;

	private String firstName;
	private String lastName;

	private Role role;

	public static User create() {
		return new User();
	}
}
