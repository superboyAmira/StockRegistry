package ru.zakharenko.javaquerytool.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import ru.zakharenko.javaquerytool.repositories.util.*;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final StringToIssuerAccConverter stringToIssuerAccConverter;
	private final StringToRegistryConverter stringToRegistryConverter;
	private final StringToSubjectConverter stringToSubjectConverter;
	private final StringToIssuerConverter StrToIssuer;
	private final StringToStockConverter StrToStock;

	public WebConfig(StringToIssuerAccConverter stringToIssuerAccConverter,
	                 StringToRegistryConverter stringToRegistryConverter,
	                 StringToSubjectConverter stringToSubjectConverter,
	                 StringToIssuerConverter s,
	                 StringToStockConverter s_stock) {
		this.stringToIssuerAccConverter = stringToIssuerAccConverter;
		this.stringToRegistryConverter = stringToRegistryConverter;
		this.stringToSubjectConverter = stringToSubjectConverter;
		this.StrToIssuer = s;
		this.StrToStock = s_stock;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(stringToIssuerAccConverter);
		registry.addConverter(stringToRegistryConverter);
		registry.addConverter(stringToSubjectConverter);
		registry.addConverter(StrToIssuer);
		registry.addConverter(StrToStock);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
