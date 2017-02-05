package com.lws.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lws.model.ClientModel;
import com.lws.model.ProductModel;
import com.lws.model.PurchaseItemModel;
import com.lws.model.PurchaseModel;
import com.lws.services.ClientServices;
import com.lws.services.PurchaseServices;

@Controller
public class PurchaseController {

	@Autowired
	private ClientServices clientServices;
	@Autowired
	private PurchaseServices purchaseServices;

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String redirectToPurchase(ModelMap map) {
		map.addAttribute("purchase", new PurchaseModel());
		return "purchase";
	}
	
	@RequestMapping(value = "/deletePurchaseModel", method = RequestMethod.GET)
	@ResponseBody
	public String deletePurchaseModel(@RequestParam int id) {
		return purchaseServices.deletePurchaseModel(id) ? "Success" : "Failure";
	}

	@RequestMapping(value = "/viewPurchases", method = RequestMethod.GET)
	public String viewPurchases() {
		return "viewPurchases";
	}

	@RequestMapping(value = "/getPurchaseList", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseModel> getPurchaseList(@RequestParam int offset,
			@RequestParam int index) {
		return purchaseServices.getList(offset, index);
	}

	@RequestMapping(value = "/getPurchaseCount", method = RequestMethod.GET)
	@ResponseBody
	public Long getPurchaseOrderCount() {
		return purchaseServices.getCount();
	}

	@RequestMapping(value = "/registerPurchase", method = RequestMethod.GET)
	@ResponseBody
	public String registerProduct(@RequestParam String mainDetails,
			@RequestParam String tableContent) {
		try {

			String[] mainValues = mainDetails.split("@&");
			String[] tableRows = tableContent.split("#");
			Set<PurchaseItemModel> productItemList = new HashSet<PurchaseItemModel>();
			for (int i = 0; i < tableRows.length; i++) {

				String[] tableData = tableRows[i].split("@&");
				System.out.println(tableData.length + " table data length - "
						+ tableData[0]);
				int quantity = Integer.parseInt(tableData[1]);
				if (quantity > 0) {
					PurchaseItemModel model = new PurchaseItemModel();
					ProductModel product = new ProductModel();
					product.setId(Integer.parseInt(tableData[0]));

					model.setProduct(product);
					model.setQuantity(Integer.parseInt(tableData[1]));
					model.setFree(Integer.parseInt(tableData[2]));
					model.setUnitRate(Float.parseFloat(tableData[3]));
					model.setDiscount(Integer.parseInt(tableData[4]));
					model.setTax(tableData[5]);
					model.setVat(Float.parseFloat(tableData[6]));
					model.setAmount(Float.parseFloat(tableData[7]));
					model.setTotalQuantity(model.getQuantity()
							+ model.getFree());
					productItemList.add(model);
				}
			}

			PurchaseModel model = new PurchaseModel();
			ClientModel client = clientServices.getClient(mainValues[0]);
			model.setClient(client);
			try {
				model.setDate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(mainValues[1]));
			} catch (Exception ex) {
				model.setDate(new Date());
			}

			model.setTotalAmount(Integer.parseInt(mainValues[2]));
			model.setRemarks1(mainValues[3]);
			model.setRemarks2(mainValues[4]);
			for(PurchaseItemModel pmi : productItemList) {
				pmi.setModel(model);
				model.getPurchaseItemList().add(pmi);
			}
			
			System.out.println(model.toString());
			return purchaseServices.savePurchase(model) ? "Success" : "failure";

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return "failure";
		}

	}
	

	@RequestMapping(value = "/getPurchaseItems", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseItemModel> getPurchaseItemModel(
			@RequestParam int purchaseId, @RequestParam int offset,
			@RequestParam int index) {
		return purchaseServices.getPurchaseItemList(purchaseId, offset, index);
	}

	@RequestMapping(value = "/getPurchaseItemsCount", method = RequestMethod.GET)
	@ResponseBody
	public Integer getPurchaseItemListCount(@RequestParam int purchaseId) {
		return purchaseServices.getPurchaseItemListCount(purchaseId);
	}

	@RequestMapping(value = "addPurchaseItemProduct", method = RequestMethod.GET)
	@ResponseBody
	public String addProduct(@RequestParam int id,
			@RequestParam int productId, @RequestParam int quantity,
			@RequestParam int free, @RequestParam float price,
			@RequestParam int discount, @RequestParam String tax,
			@RequestParam float vat, @RequestParam float amt) {
		
		PurchaseItemModel model = new PurchaseItemModel();
		ProductModel product=  new ProductModel();
		product.setId(productId);
		model.setProduct(product);
		model.setQuantity(quantity);
		model.setFree(free);
		model.setDiscount(discount);
		model.setUnitRate(price);
		model.setVat(vat);
		model.setTax(tax);
		model.setAmount(amt);
		
		
		System.out.println(model.getProduct().getId() +" - "+ model.getAmount()+" - "+model.getQuantity() );
		return purchaseServices.addPurchaseItem(model, id) ? "Success" : "Failure";

	}
	
	@RequestMapping(value = "deletePurchaseItem", method = RequestMethod.GET)
	@ResponseBody
	public String deleteProduct(@RequestParam int purchaseId, @RequestParam int purchaseItemId) {
		return purchaseServices.removePurchaseItem(purchaseServices.getPurchaseItem(purchaseItemId), purchaseId) ? "Success" : "failure";
		
	}
	@RequestMapping(value= "getPurchase", method = RequestMethod.GET)
	@ResponseBody
	public PurchaseItemModel getPurchase(@RequestParam int id ) {
		return purchaseServices.getPurchaseItem(id);
	}
	
	@RequestMapping(value = "modifyPurchaseDetails", method = RequestMethod.GET)
	@ResponseBody
	public String modifyProduct(@RequestParam int id,@RequestParam String consignee,@RequestParam  String date,@RequestParam  String remarks1,@RequestParam  String remarks2) {
		PurchaseModel model = new PurchaseModel();
		model.setRemarks1(remarks1);
		model.setRemarks2(remarks2);
		try {
			model.setDate(new SimpleDateFormat("yyyy-MM-dd")
			.parse(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.setDate(new java.util.Date());
		}
		
		model.setClient(clientServices.getClient(consignee));
		return purchaseServices.updatePurchase(model, id) ? "Success" : "failure";
	}

}
