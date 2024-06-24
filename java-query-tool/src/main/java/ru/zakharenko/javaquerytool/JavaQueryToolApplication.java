package ru.zakharenko.javaquerytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.zakharenko.javaquerytool.repositories.util.HibernateUtil;


@SpringBootApplication
public class JavaQueryToolApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaQueryToolApplication.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread(HibernateUtil::shutdown));
	}
}
