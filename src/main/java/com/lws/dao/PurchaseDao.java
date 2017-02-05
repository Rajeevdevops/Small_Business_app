package com.lws.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lws.model.PurchaseItemModel;
import com.lws.model.PurchaseModel;
import com.lws.services.PurchaseServices;

@Service("purchaseServices")
@Repository
@Transactional 
public class PurchaseDao implements PurchaseServices{

	private SessionFactory factory;
	private static final Logger logger = LoggerFactory.getLogger(PurchaseDao.class);
	
	@Autowired
	public PurchaseDao(SessionFactory factory) {
		this.factory = factory;
	}


	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean savePurchase(PurchaseModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			System.out.println("Transaction begun");
			Integer id = (Integer)session.save(model);
			System.out.println(id != null ? id : "id value is null");
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Error in saving purchase {} ", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}


	@SuppressWarnings("unchecked")
	public List<PurchaseModel> getList(int offset, int index) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Query query = session.createQuery("select model.id, model.client.name, model.totalAmount, model.date, model.remarks1, model.remarks2 from PurchaseModel model order by model.date desc");
			query.setFirstResult(index);
			query.setMaxResults(offset);
			return (List<PurchaseModel>) query.list();
		} catch (HibernateException ex) {
			logger.error("Error in getting list", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	


	public Long getCount() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (Long) session.createQuery("select count(*) from PurchaseModel").uniqueResult();
		} catch (HibernateException ex) {
			logger.error("Error in getting Count", ex.getMessage());
			return 0l;
		} finally {
			session.close();
		}
	}


	@SuppressWarnings("unchecked")
	public List<PurchaseItemModel> getPurchaseItemList(int purchaseId, int offset, int index) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Query query = session.createQuery("select purchase.id, purchase.product, purchase.quantity, purchase.free, purchase.unitRate, purchase.discount, purchase.vat, purchase.amount, purchase.tax from PurchaseItemModel purchase where purchase.model.id = :id").setParameter("id",  purchaseId);
			query.setFirstResult(index);
			query.setMaxResults(offset);
			return (List<PurchaseItemModel>) query.list();
		} catch (HibernateException ex) {
			logger.error("Error in getting Purchase Item List {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}


	public Integer getPurchaseItemListCount(int id) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return session.createQuery("select purchase.purchaseItemList from PurchaseModel purchase where purchase.id = :id").setParameter("id", id).list().size();
		} catch (HibernateException ex) {
			logger.error("Exception is at getPurchaseItemCount {}",ex.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean removePurchaseItem(PurchaseItemModel item, int purchaseId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {

			int rows = session.createQuery("delete from PurchaseItemModel purchase where purchase.id = :id").setParameter("id", item.getId()).executeUpdate();
			if(rows > 0) {
				double amount = (Double)session.createQuery("select sum(purchase.amount) from PurchaseItemModel purchase where purchase.model.id = :id").setParameter("id", purchaseId).uniqueResult();
				int total = (int) Math.round(amount);
				session.createQuery("update PurchaseModel model set model.totalAmount = :amount").setParameter("amount", total).executeUpdate();
			}
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Exception is at removingPurchaseItem {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}
	

	
	
	public PurchaseItemModel getPurchaseItem(int id) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (PurchaseItemModel) session.get(PurchaseItemModel.class, id);
		} catch (HibernateException ex) {
			logger.error("Excepiton in getting PurchaseItemModel {}", ex.getMessage());
			System.out.println(ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean addPurchaseItem(PurchaseItemModel model, int purchaseId) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			PurchaseModel purchase = (PurchaseModel) session.get(PurchaseModel.class, purchaseId);
			Set<PurchaseItemModel> set = purchase.getPurchaseItemList();
			model.setModel(purchase);
			set.add(model);
			int totalAmount = purchase.getTotalAmount() + Math.round( model.getAmount() );
			purchase.setTotalAmount(totalAmount);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Exception is at addingPurchaseItem {}", ex.getMessage());
			return true;
		} finally {
			session.close();
		}
	}


	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean updatePurchase(PurchaseModel model, int id) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			System.out.println("Transaction begun");
			PurchaseModel purchase = (PurchaseModel) session.get(PurchaseModel.class, id);
			purchase.setClient(model.getClient());
			purchase.setDate(model.getDate());
			purchase.setRemarks1(model.getRemarks1());
			purchase.setRemarks2(model.getRemarks2());
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Exception in updating purchase product {} ", ex.getMessage());
			System.out.println(ex.getMessage());
			return false;
		} finally {
			session.close();
			
		}
	}

	@Transactional (readOnly = false, rollbackFor = HibernateException.class)
	public boolean deletePurchaseModel(int id) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			
			PurchaseModel model = (PurchaseModel) session.get(PurchaseModel.class, id);
			session.delete(model);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Exception in deleting purchase model {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}
	
	
	
	
}
