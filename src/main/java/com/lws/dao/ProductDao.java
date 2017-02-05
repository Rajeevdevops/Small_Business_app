package com.lws.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lws.model.ProductModel;
import com.lws.services.ProductServices;

@Service("productServices")
@Repository
@Transactional
public class ProductDao implements ProductServices {
	
	

	private SessionFactory factory;
	private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

	@Autowired
	public ProductDao(SessionFactory factory) {
		this.factory = factory;
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean saveProduct(ProductModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			session.save(model);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Error in saveProduct: {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}

	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProductModel> getList(int offset, int index) {
		
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Criteria  criteria = session.createCriteria(ProductModel.class);
			criteria.setFirstResult(index);
			criteria.setMaxResults(offset);
			return (List<ProductModel>)criteria.list();
		} catch (HibernateException ex) {
			logger.error("Error in getList {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public ProductModel getProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getProductNames(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductModel getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean deleteProduct(ProductModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			int count = session.createQuery("delete from ProductModel model where model.id = :id").setParameter("id", model.getId()).executeUpdate();
			session.getTransaction().commit();
			return count > 0;
		} catch (HibernateException ex) {
			logger.error("Error in deleting Product {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean modifyProduct(ProductModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			session.update(model);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Error modify product is {}", ex.getMessage());
			return true;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly=true)
	public Long getCount() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (Long)session.createQuery("select count(*) from ProductModel model").uniqueResult();
		} catch (HibernateException ex) {
			logger.error("Exception is getCount{}", ex.getMessage());
			return 0l;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProductModel> getProductNameId() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<ProductModel>) session.createQuery("select model.id, model.name, model.units from ProductModel model order by model.name asc").list();
		} catch (HibernateException ex) {
			logger.error("Exception is getProducts {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	

}
