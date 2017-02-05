package com.lws.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lws.dao.ClientDao;
import com.lws.model.ClientModel;

public interface ClientServices {
	Logger logger = LoggerFactory.getLogger(ClientDao.class);
	boolean saveClient(ClientModel model);
	boolean updateClient(ClientModel model);
	boolean deleteClient(int id);
	List<ClientModel> getList(int index, int offset);
	Long getCount();
	List<String> getClientNames(String name);
	List<String> getNames();
	ClientModel getClient(String name);
	ClientModel getClient(int id);
}
