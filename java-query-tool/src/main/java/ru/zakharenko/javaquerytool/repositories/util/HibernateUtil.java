package ru.zakharenko.javaquerytool.repositories.util;
/*
hibernate.connection.driver_class = org.postgresql.Driver
hibernate.connection.url = jdbc:postgresql://localhost:5442/query-tool
hibernate.connection.username = postgres
hibernate.connection.password = postgres
hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
hibernate.show_sql = true
hibernate.hbm2ddl.auto = update

 */

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	@Getter
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.Issuer.class);
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.Registry.class);
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.IssuerAcc.class);
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.Stock.class);
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.Subject.class);
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.Operation.class);
			configuration.addAnnotatedClass(ru.zakharenko.javaquerytool.models.Payment.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
