package com.lws.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lws.model.QuotationItemModel;
import com.lws.model.QuotationModel;
import com.lws.services.QuotationServices;

@Service("quotationServices")
@Repository
@Transactional
public class QuotationDao implements QuotationServices {

	private SessionFactory factory;
	

	@Autowired
	public QuotationDao(SessionFactory factory) {
		this.factory = factory;
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger(QuotationDao.class);

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean saveQuotation(QuotationModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			session.save(model);
			session.getTransaction().commit();
			return  true;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
			logger.error("Error in saving quotation : {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuotationModel> getTodaysList() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<QuotationModel>) session.createQuery("from QuotationModel quot where quot.date = :date order by quot.id asc")
					.setParameter("date", new java.util.Date())
					.list();
		} catch (HibernateException ex) {
			logger.error("Exception in getting todays list "+ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuotationModel> getQuotations(Date fromDate, Date toDate) {
		Session session  = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<QuotationModel>) session.createQuery("from QuotationModel quot where quot.date between :fromDate and :toDate")
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.list();
		} catch (HibernateException ex) {
			logger.error("Exception in getQuotation -> dates {} ", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuotationModel> getQuotations(int offset, int index) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<QuotationModel>) 
					session.createQuery("select quot.id, quot.client.name, quot.totalAmount, quot.totalQuantity, quot.reference, quot.phone, quot.date from QuotationModel quot order by quot.id asc")
					.setFirstResult(index)
					.setMaxResults(offset)
					.list();
		} catch (HibernateException ex) {
			logger.error("Error in getting quotations {}", ex.getMessage());
			System.out.println(ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean deleteQuotation(int quotationId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			QuotationModel model = (QuotationModel)session.get(QuotationModel.class, quotationId);
			session.delete(model);
			session.getTransaction().commit();
			return true;
		} catch(HibernateException ex) {
			logger.error("Error in deleting the quotatioin {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	public Long getQuotationCount() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (Long) session.createQuery("select count(*) from QuotationModel ").uniqueResult();
		} catch (HibernateException ex) {
			logger.error("Exception in getting count of quotations  {}", ex.getMessage());
			return 0l;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<QuotationItemModel> getQuotationItems(int quotId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<QuotationItemModel>) session.createQuery("select product.name, product.units, quantity, unitRate, discount, tax, vat, totalAmount from QuotationItemModel where quotation.id = :quotId")
					.setParameter("quotId", quotId)
					.list();
					
			
		} catch (HibernateException ex) {
			logger.error("Exception in getting QuotationItems {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public QuotationModel getQuotation(int quotId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (QuotationModel) session.get(QuotationModel.class, quotId);
		} catch (HibernateException ex) {
			logger.error("Exception in getQuotation {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	} 

}
