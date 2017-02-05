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

import com.lws.model.ProductModel;
import com.lws.model.QuotationItemModel;
import com.lws.model.QuotationModel;
import com.lws.services.ClientServices;
import com.lws.services.ProductCredServices;
import com.lws.services.QuotationServices;

@Controller
public class QuotationController {

	@Autowired
	private ProductCredServices productCredServices;
	@Autowired
	private ClientServices clientServices;
	@Autowired
	private QuotationServices quotationServices;
	
	@RequestMapping(value = "quotation", method = RequestMethod.GET)
	public String getQuotation(ModelMap map) {
		map.addAttribute("quotation", new QuotationModel());
		return "quotation";
	}
	
	@RequestMapping(value = "viewQuotations", method = RequestMethod.GET)
	public String viewQuotations() {
		return "viewQuotations";
	}
	
	
	
	
	@RequestMapping(value = "/getProductCred", method = RequestMethod.GET)
	@ResponseBody
	public String getProductCredentials(@RequestParam String productId) {
		int pid = Integer.parseInt(productId);
		int productQuantity = productCredServices.getProductQuantity(pid);
		int purchaseQuantity = productCredServices.getPurchaseQuantity(pid);
		float unitRate = productCredServices.getUnitRate(pid);
		int invoiceQuantity = productCredServices.getInvoiceQuantity(pid);
		int availableQuantity = ((productQuantity + purchaseQuantity) - invoiceQuantity);
		return unitRate + ":"+ availableQuantity;
	}
	
	@RequestMapping(value = "generateQuotation", method = RequestMethod.GET)
	@ResponseBody
	public String generateQuotation(@RequestParam String htmlData, @RequestParam String productData) {
		try {
			String quotValues[] = htmlData.split("@&");
			String products[] = productData.split("##");
			List<QuotationItemModel> list = new ArrayList<QuotationItemModel>();
			for(int i = 0; i < products.length; i++) {
				String[] values = products[i].split("@&");
				QuotationItemModel model = new QuotationItemModel();
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
			
			QuotationModel quotation = new QuotationModel();
			quotation.setClient(quotValues[0].equals("no") ? clientServices.getClient(0) : clientServices.getClient(quotValues[0]));
			quotation.setDate(new java.util.Date());
			quotation.setTotalAmount(Math.round(Float.parseFloat(quotValues[1])));
			quotation.setTotalQuantity(Integer.parseInt(quotValues[2]));
			quotation.setReference(quotValues[3]);
			quotation.setPhone(quotValues[4]);
			quotation.setClaimPercentage(Integer.parseInt(quotValues[5]));
			quotation.setClaimAmount(Float.parseFloat(quotValues[6]));
			for(QuotationItemModel model : list) {
				model.setQuotation(quotation);
				quotation.getQuotationItemSet().add(model);
			}
			
			return quotationServices.saveQuotation(quotation) ? "Success" : "failure";
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "failure";
		}
	}
	
}
