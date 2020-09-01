package by.pvt.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime dateTime = LocalDateTime.parse(source, formatter);
		return dateTime;
	}

}
