package by.pvt.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDetailsDTO {

	@Length(min = 5)
	private String password;
	private String login;
	@Email
	private String email;

}
