package ru.zakharenko.javaquerytool.services;

import jdk.jfr.Unsigned;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import ru.zakharenko.javaquerytool.models.Operation;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.repositories.util.HibernateUtil;

import java.util.List;

@Service
public class QueryService {
	public List<String> getAllForms() {
		Transaction tr = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("SELECT s.form FROM Stock s", String.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Stock> getAllStockIndividual(String type) {
		Transaction tr = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
							"SELECT s FROM Stock s LEFT JOIN s.subject sub WHERE sub.subject_type = :type",
							Stock.class)
					.setParameter("type", type)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Operation> getAllBonds(String form) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT o FROM Operation o LEFT JOIN o.stock s WHERE s.form = :form";
			return session.createQuery(hql, Operation.class).setParameter("form",form).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object[]> getAllInfoCorporate(String subject_type) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql1 = "SELECT o.id, o.operationDate, b.contact_info, st.form, st.quote "
					+ "FROM Operation o "
					+ "LEFT JOIN o.buyerSubject b "
					+ "LEFT JOIN o.stock st "
					+ "WHERE b.subject_type = :subject_type";
			return session.createQuery(hql1).setParameter("subject_type", subject_type).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object[]> getAllInfoStocksMoreThan1000(Long nominalVal) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql1 = "SELECT p.id, p.amount, i.company, st.form, st.nominalValue "
					+ "FROM Payment p "
					+ "LEFT JOIN p.issuer i "
					+ "LEFT JOIN p.stock st "
					+ "WHERE st.nominalValue > :val";
			return session.createQuery(hql1).setParameter("val", nominalVal).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
