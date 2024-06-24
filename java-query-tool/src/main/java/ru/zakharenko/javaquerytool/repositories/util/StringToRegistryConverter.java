package ru.zakharenko.javaquerytool.repositories.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.zakharenko.javaquerytool.models.Registry;
import ru.zakharenko.javaquerytool.services.RegistryService;

import java.util.UUID;

@Component
public class StringToRegistryConverter implements Converter<String, Registry> {
	private final RegistryService ser;

	public StringToRegistryConverter(RegistryService ser) {
		this.ser = ser;
	}

	@Override
	public Registry convert(String source) {
		return ser.getById(UUID.fromString(source));
	}
}
