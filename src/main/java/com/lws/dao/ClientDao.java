package com.lws.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lws.model.ClientModel;
import com.lws.services.ClientServices;

@Service("clientServices")
@Repository
@Transactional
public class ClientDao implements ClientServices {

	private SessionFactory factory;

	@Autowired
	public ClientDao(SessionFactory factory) {
		this.factory = factory;
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean saveClient(ClientModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			session.save(model);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Exception in saveClient is {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean updateClient(ClientModel model) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			session.update(model);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException ex) {
			logger.error("Exception in updateClient is {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public boolean deleteClient(int id) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			int count = session
					.createQuery(
							"delete from ClientModel model where model.id = :id")
					.setParameter("id", id).executeUpdate();
			session.getTransaction().commit();
			return count > 0;
		} catch (HibernateException ex) {
			logger.error("Exception in saveClient is {}", ex.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ClientModel> getList(int index, int offset) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			Criteria criteria = session.createCriteria(ClientModel.class);
			criteria.setFirstResult(index);
			criteria.setMaxResults(offset);
			return (List<ClientModel>) criteria.list();
		} catch (HibernateException ex) {
			logger.error("Exception in getList is {} ", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Long getCount() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (Long) session.createQuery("select count(*) from ClientModel model").uniqueResult();
		} catch (HibernateException ex) {
			logger.error("Exception in getCount is {}", ex.getMessage());
			return 0l;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getClientNames(String name) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<String>) session.createQuery("select model.name from ClientModel model where model.name like '"+name+"%'").list();
		} catch(HibernateException ex) {
			logger.error("Exception in getting Client Names {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getNames() {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (List<String>) session.createQuery("select model.name from ClientModel model order by model.name asc").list();
		} catch (HibernateException ex) {
			logger.error("Exception in getting names {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public ClientModel getClient(String name) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (ClientModel) session.createQuery("from ClientModel model where model.name = :name").setParameter("name", name).uniqueResult();
		} catch (HibernateException ex) {
			logger.error("Exception in getting Client {}", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public ClientModel getClient(int id) {
		Session session = factory.openSession();
		session.getTransaction().begin();
		try {
			return (ClientModel)session.get(ClientModel.class, id);
		} catch (HibernateException ex) {
			logger.error("Exception in retrieving client details {} ", ex.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

}
