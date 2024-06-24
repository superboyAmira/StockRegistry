package ru.zakharenko.javaquerytool.services;

import org.springframework.stereotype.Service;
import ru.zakharenko.javaquerytool.models.IssuerAcc;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.zakharenko.javaquerytool.repositories.util.HibernateUtil;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class IssuerAccService implements BaseService<IssuerAcc, Long> {
	@Override
	public IssuerAcc create(IssuerAcc entity) {
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
	public IssuerAcc getById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(IssuerAcc.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getMaxId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> query = builder.createQuery(Long.class);
			Root<IssuerAcc> root = query.from(IssuerAcc.class);
			query.select(builder.max(root.get("id")));
			return session.createQuery(query).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IssuerAcc> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from IssuerAcc", IssuerAcc.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IssuerAcc update(IssuerAcc entity) {
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
	public void delete(Long id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			IssuerAcc issuer = session.get(IssuerAcc.class, id);
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
