package ru.zakharenko.javaquerytool.repositories.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.zakharenko.javaquerytool.models.Subject;
import ru.zakharenko.javaquerytool.services.SubjectService;

import java.util.UUID;

@Component
public class StringToSubjectConverter implements Converter<String, Subject> {
	private final SubjectService ser;

	public StringToSubjectConverter(SubjectService ser) {
		this.ser = ser;
	}

	@Override
	public Subject convert(String source) {

		return ser.getById(UUID.fromString(source));
	}
}
