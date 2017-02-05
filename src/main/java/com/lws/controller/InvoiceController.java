package com.lws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lws.model.InvoiceItemModel;
import com.lws.model.InvoiceModel;
import com.lws.model.ProductModel;
import com.lws.model.QuotationItemModel;
import com.lws.model.QuotationModel;
import com.lws.services.ClientServices;
import com.lws.services.InvoiceServices;
import com.lws.services.ProductCredServices;
import com.lws.services.QuotationServices;

@Controller
public class InvoiceController {

	@Autowired
	private QuotationServices quotationServices;
	@Autowired
	private ProductCredServices productCredServices;
	@Autowired
	private InvoiceServices invoiceServices;
	@Autowired
	private ClientServices clientServices;
	
	@RequestMapping(value = "generateInvoice", method = RequestMethod.GET)
	
	public String getInvoice(@RequestParam int quotId, ModelMap map) {
		InvoiceModel model = new InvoiceModel();
		QuotationModel quotation = quotationServices.getQuotation(quotId);
		
		model.setClaimAmount(quotation.getClaimAmount());
		model.setClaimPercentage(quotation.getClaimPercentage());
		model.setDate(new java.util.Date());
		model.setClient(quotation.getClient());
		model.setPhone(quotation.getPhone());
		model.setQuotId(quotation.getId());
		model.setReference(quotation.getReference());
		model.setTotalAmount(quotation.getTotalAmount());
		model.setTotalQuantity(quotation.getTotalQuantity());
		for(QuotationItemModel quotItems : quotation.getQuotationItemSet()) {
			InvoiceItemModel invoice = new InvoiceItemModel();
			invoice.setInvoice(model);
			invoice.setDiscount(quotItems.getDiscount());
			invoice.setAvailableUnitPrice(productCredServices.getUnitRate(quotItems.getProduct().getId()));
			invoice.setProduct(quotItems.getProduct());
			invoice.setQuantity(quotItems.getQuantity());
			invoice.setTax(quotItems.getTax());
			invoice.setTotalAmount(quotItems.getTotalAmount());
			invoice.setUnitRate(quotItems.getUnitRate());
			invoice.setVat(quotItems.getVat());
			model.getQuotationItemSet().add(invoice);
		}
		map.addAttribute("invoice", model);
		return "generateInvoice";
	}
	
	@RequestMapping(value = "invoice", method = RequestMethod.GET)
	public String getInvoices(ModelMap map) {
		return "invoices";
	}
	
	@RequestMapping(value = "registerInvoice", method = RequestMethod.GET)
	@ResponseBody
	public String registerInvoice(@RequestParam String htmlData, @RequestParam String productData) {
		String quotValues[] = htmlData.split("@&");
		String products[] = productData.split("##");
		List<InvoiceItemModel> list = new ArrayList<InvoiceItemModel>();
		for(int i = 0; i < products.length; i++) {
			String[] values = products[i].split("@&");
			InvoiceItemModel model = new InvoiceItemModel();
			ProductModel product = new ProductModel();
			product.setId(Integer.parseInt(values[0]));
			model.setProduct(product);
			model.setQuantity(Integer.parseInt(values[1]));
			model.setUnitRate(Float.parseFloat(values[2]));
			model.setDiscount(Integer.parseInt(values[3]));
			model.setTax(values[4]);
			model.setVat(Float.parseFloat(values[5]));
			model.setTotalAmount(Float.parseFloat(values[6]));
			model.setAvailableUnitPrice(Float.parseFloat(values[7]));
	        
			list.add(model);
		}
		
		InvoiceModel invoice = new InvoiceModel();
		invoice.setClient(quotValues[0].equals("no") ? clientServices.getClient(0) : clientServices.getClient(quotValues[0]));
		invoice.setDate(new java.util.Date());
		invoice.setTotalAmount(Math.round(Float.parseFloat(quotValues[1])));
		invoice.setTotalQuantity(Integer.parseInt(quotValues[2]));
		invoice.setReference(quotValues[3]);
		invoice.setPhone(quotValues[4]);
		invoice.setClaimPercentage(Integer.parseInt(quotValues[5]));
		invoice.setClaimAmount(Float.parseFloat(quotValues[6]));
		invoice.setQuotId(Integer.parseInt(quotValues[7]));
		for(InvoiceItemModel model : list) {
			model.setInvoice(invoice);
			invoice.getQuotationItemSet().add(model);
		}
		
		return invoiceServices.saveInvoice(invoice) ? "Success" : "failure";
	}
}
