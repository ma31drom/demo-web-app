package by.pvt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "by.pvt")
@Data
public class DataSourceConfigProperties {

	private String url;
	private String username;
	private String password;
	private String driverClass;
}
