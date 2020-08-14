package by.pvt.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

//@Configuration
//@EnableConfigurationProperties(DataSourceConfigProperties.class)
public class DataSourceConfiguration {

	//@Bean
	public DataSource ourOunDataSource(DataSourceConfigProperties props)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return new SimpleDriverDataSource((java.sql.Driver) Class.forName(props.getDriverClass()).newInstance(),
				props.getUrl(), props.getUsername(), props.getPassword());
	}

}
