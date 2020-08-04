package by.pvt.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "USER_TABLE")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String login;
	private String password;
	private String firstName;
	private String lastName;

}
