package ru.zakharenko.javaquerytool.repositories.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.zakharenko.javaquerytool.models.Issuer;
import ru.zakharenko.javaquerytool.services.IssuerService;

import java.util.UUID;

@Component
public class StringToIssuerConverter implements Converter<String, Issuer> {
	private final IssuerService ser;

	public StringToIssuerConverter(IssuerService ser) {
		this.ser = ser;
	}

	@Override
	public Issuer convert(String source) {

		return ser.getById(UUID.fromString(source));
	}
}