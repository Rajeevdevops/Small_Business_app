package com.lws.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lws.services.ProductCredServices;
@Service("productCredServices")
@Repository
@Transactional
public class ProductCredDao implements ProductCredServices{

	
	private SessionFactory factory;
	
	@Autowired
	public ProductCredDao(SessionFactory factory) {
		this.factory = factory;
	}

	public int getPurchaseQuantity(int productId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Long count = (Long)session.createQuery("select sum(purchase.totalQuantity) from PurchaseItemModel purchase where purchase.product.id = :id")
					.setParameter("id", productId)
					.uniqueResult();
			return count != null ? (Integer.parseInt(count+"")) : 0;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	public int getProductQuantity(int productId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Integer count = (Integer) session.createQuery("select quantity from ProductModel where id = :id").setParameter("id", productId).uniqueResult();
			return count != null ?  count :  0;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	public float getUnitRate(int productId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Integer maxId = (Integer)session.createQuery("select max(id) from PurchaseItemModel where product.id = :id").setParameter("id", productId).uniqueResult();
			
			if(maxId != null) {
			Float rate = (Float) session.createQuery("select purchase.unitRate from PurchaseItemModel purchase where purchase.id = :id")
					.setParameter("id", maxId).uniqueResult();
			System.out.println(rate); 
			return rate != null ? rate : 0;
			} return 0;
			
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	public int getInvoiceQuantity(int productId) {
		return  0 ;
	}
	
	

}
