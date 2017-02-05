package com.lws.services;

import java.util.Date;
import java.util.List;

import com.lws.model.QuotationItemModel;
import com.lws.model.QuotationModel;

public interface QuotationServices {
	
	boolean saveQuotation(QuotationModel model);
	List<QuotationModel> getTodaysList();
	List<QuotationModel> getQuotations(Date fromDate, Date toDate);
	List<QuotationModel> getQuotations(int offset, int index);
	boolean deleteQuotation(int quotationId);
	Long getQuotationCount();
	List<QuotationItemModel> getQuotationItems(int quotId);
	QuotationModel getQuotation(int quotId);
	
}
