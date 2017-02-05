package com.lws.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lws.model.InvoiceModel;
import com.lws.services.InvoiceServices;

@Service("invoiceServices")
@Repository
@Transactional
public class InvoiceDao implements InvoiceServices{

	private SessionFactory factory;
	private Logger logger = LoggerFactory.getLogger(InvoiceDao.class);
	@Autowired
	public InvoiceDao(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean saveInvoice(InvoiceModel invoice) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			session.save(invoice);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			System.out.println("Exception ion Saving Invoice : "+ex.getMessage());
			logger.error("Exception in saving Invoice : "+ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}
	
}
