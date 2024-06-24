package ru.zakharenko.javaquerytool.repositories.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.services.StockService;

import java.util.UUID;

@Component
public class StringToStockConverter implements Converter<String, Stock> {
	private final StockService ser;

	public StringToStockConverter(StockService ser) {
		this.ser = ser;
	}

	@Override
	public Stock convert(String source) {
		return ser.getById(UUID.fromString(source));
	}
}