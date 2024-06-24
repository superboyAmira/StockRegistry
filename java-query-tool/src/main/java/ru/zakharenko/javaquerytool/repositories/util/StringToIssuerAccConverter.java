package ru.zakharenko.javaquerytool.repositories.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.zakharenko.javaquerytool.models.IssuerAcc;
import ru.zakharenko.javaquerytool.services.IssuerAccService;

@Component
public class StringToIssuerAccConverter implements Converter<String, IssuerAcc> {

	private final IssuerAccService service;

	public StringToIssuerAccConverter(IssuerAccService issuerAccService) {
		this.service = issuerAccService;
	}

	@Override
	public IssuerAcc convert(String source) {
		return service.getById(Long.valueOf(source));
	}
}