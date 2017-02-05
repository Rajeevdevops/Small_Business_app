package com.lws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lws.model.QuotationItemModel;
import com.lws.model.QuotationModel;
import com.lws.services.QuotationServices;

@Controller
public class QuotationItemController {

	@Autowired
	private QuotationServices quotationServices;
	
	
	@RequestMapping(value = "/quotCredentials", method = RequestMethod.GET)
	@ResponseBody
	public List<QuotationModel> getQuotationsList(@RequestParam int offset,@RequestParam int index) {
		return quotationServices.getQuotations(offset, index); 
	}
	
	
	@RequestMapping(value = "/getQuotCount", method = RequestMethod.GET)
	@ResponseBody
	public Long getCount() {
		return quotationServices.getQuotationCount();
	}
	
	
	@RequestMapping(value = "/getQuotationItems", method = RequestMethod.GET)
	@ResponseBody
	public List<QuotationItemModel> getQuotationItems(@RequestParam int quotId) {
		return quotationServices.getQuotationItems(quotId);
	}
	
}
