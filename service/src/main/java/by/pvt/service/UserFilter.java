package by.pvt.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFilter {

	private String login;
	private String password;
	private String firstName;
	private String lastName;

}
