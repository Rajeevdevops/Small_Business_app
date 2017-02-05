package com.lws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lws.model.ProductModel;
import com.lws.services.ProductServices;

@Controller
public class ProductController {

	@Autowired
	private ProductServices productServices;

	@RequestMapping(value = "product", method = RequestMethod.GET)
	public ModelAndView productPage() {
		System.out.println("Control is here");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product");
		modelAndView.addObject("product", new ProductModel());
		return modelAndView;
	}

	@RequestMapping(value = "deleteProduct", method = RequestMethod.GET) 
	@ResponseBody
	public String deleteProduct(@RequestParam int id) {
		ProductModel model = new ProductModel();
		model.setId(id);
		System.out.println(id);
		boolean flag = productServices.deleteProduct(model);
		String message = flag ? "Success": "Failure";
		System.out.println(message);
		return message;
	}
	
	@RequestMapping(value = "modifyProduct", method = RequestMethod.GET)
	@ResponseBody
	public String modifyProduct(@RequestParam int id, @RequestParam String name, @RequestParam String units,
			@RequestParam String desc1, @RequestParam String desc2, @RequestParam String desc3, @RequestParam String desc4,
			@RequestParam int discount, @RequestParam int quanityRate, @RequestParam int quantity) {
		
		ProductModel model =  new ProductModel(id, discount, quanityRate, name, desc1, desc2, desc3, desc4, units);
		model.setQuantity(quantity);
		boolean flag = productServices.modifyProduct(model);
		
		String message = flag ? "success" : "failure";
		return message;

	}

	@RequestMapping(value = "viewAllProducts", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductModel> getList(@RequestParam("offset") int offset,
			@RequestParam("index") int index) {
		System.out.println(offset + " - " + index);
		List<ProductModel> list = productServices.getList(offset, index);
		System.out.println(list.size());
		return list;
	}

	@RequestMapping(value = "getCount", method = RequestMethod.GET)
	@ResponseBody
	public Long getCount() {
		return productServices.getCount();
	}

	@RequestMapping(value = "viewProducts", method = RequestMethod.GET)
	public String viewProducts() {
		return "viewProducts";
	}
	
	@RequestMapping(value = "getProductNamesAndId",  method = RequestMethod.GET)
	@ResponseBody
	public List<ProductModel> getProductNames() {
		
		List<ProductModel> list = productServices.getProductNameId();
		return list;
	}

	@RequestMapping(value = "product", method = RequestMethod.POST)
	public ModelAndView productRegistration(
			@ModelAttribute(value = "product") ProductModel productModel) {
		if (productModel.getDesc1() == null) {
			productModel.setDesc1("--");
		}

		if (productModel.getDesc2() == null) {
			productModel.setDesc2("--");
		}

		if (productModel.getDesc3() == null) {
			productModel.setDesc3("--");
		}

		if (productModel.getDesc4() == null) {
			productModel.setDesc4("--");
		}
		boolean flag = productServices.saveProduct(productModel);
		String message = flag ? "Registered Successfully..!" : "Not Registered";
		String type = flag ? "Success" : "failure";
		ModelAndView model = new ModelAndView();
		model.setViewName("product");
		model.addObject("message", message);
		model.addObject("type", type);
		
		if (flag) {
			model.addObject("product", new ProductModel());
		}
		return model;
	}

}
