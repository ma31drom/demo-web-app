package by.pvt.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "email.config")
public class EmailProperties {

	private String login;
	private String pass;
	private String host;
	private String port;
	private String ssl;
	private String auth;
}
