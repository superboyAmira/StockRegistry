package ru.zakharenko.javaquerytool.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import ru.zakharenko.javaquerytool.models.Issuer;
import ru.zakharenko.javaquerytool.repositories.util.HibernateUtil;

import java.util.List;
import java.util.UUID;
@Service
public class IssuerService implements BaseService<Issuer, UUID> {

	@Override
	public Issuer create(Issuer entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			return entity;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Issuer getById(UUID id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Issuer.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Issuer> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Issuer", Issuer.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Issuer update(Issuer entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			return entity;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(UUID id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Issuer issuer = session.get(Issuer.class, id);
			if (issuer != null) {
				session.delete(issuer);
				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
