package com.cts.shopping.cart.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class TimeFormatter {
	// public long timestampInMillis = 1714389884808L;

	public String format(long timestampInMillis) {

		TimeZone defaultTimeZone = TimeZone.getDefault();
		System.out.println("Default time zone: " + defaultTimeZone.getDisplayName());
		Instant instant = Instant.ofEpochMilli(timestampInMillis);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(defaultTimeZone.getID()));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = localDateTime.format(formatter);

		System.out.println(formattedDateTime);
		return formattedDateTime;
	}
}
