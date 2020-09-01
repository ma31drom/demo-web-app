package by.pvt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateTest {

	@Test
	public void testDateParsing() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH");
		LocalDateTime dateTime = LocalDateTime.parse("2020/09/01 11", formatter);
		
		System.out.println();
	}

}
