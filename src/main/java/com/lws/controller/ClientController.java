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

import com.lws.model.ClientModel;
import com.lws.services.ClientServices;

@Controller
public class ClientController {

	@Autowired
	private ClientServices clientServices;

	@RequestMapping(value = "client", method = RequestMethod.GET)
	public ModelAndView redirectToClient() {
		ModelAndView model = new ModelAndView();
		model.setViewName("client");
		model.addObject("client", new ClientModel());
		return model;
	}
	
	@RequestMapping(value = "viewClients", method = RequestMethod.GET)
	public String viewClients() {
		return "viewClients";
	}

	
	@RequestMapping(value = "client", method = RequestMethod.POST)
	public ModelAndView registerClient(
			@ModelAttribute(value = "client") ClientModel client) {
		ModelAndView model = new ModelAndView();
		boolean flag = clientServices.saveClient(client);
		String message = flag ? "Successfully Registered"
				: "Not Registered.. Please Try again..!";
		String type = flag ? "Success" : "failure";
		model.addObject("message", message);
		model.addObject("type", type);
		if (flag) {
			model.addObject("client", new ClientModel());
		}
		model.setViewName("client");
		return model;
	}

	
	@RequestMapping(value = "getClientCount", method = RequestMethod.GET)
	@ResponseBody
	public Long getCount() {
		return clientServices.getCount();
	}

	
	@RequestMapping(value = "modifyClient", method = RequestMethod.GET)
	@ResponseBody
	public String modifyClient(@RequestParam int id, @RequestParam String name,
			@RequestParam String address, @RequestParam String tinNo, @RequestParam String cstNo,
			@RequestParam String desc1, @RequestParam String desc2, @RequestParam String remarks, 
			@RequestParam String phoneNumber1, @RequestParam String phoneNumber2, @RequestParam String email,
			@RequestParam String fax) {

		ClientModel model = new ClientModel(id, name, address, tinNo, cstNo, desc1, desc2, remarks);
		model.setPhoneNumber1(phoneNumber1);
		model.setPhoneNumber2(phoneNumber2);
		model.setEmail(email);
		model.setFax(fax);
		return clientServices.updateClient(model) ? "Success" : "failure";
	}
	
	@RequestMapping(value = "deleteClient", method = RequestMethod.GET)
	@ResponseBody
	public String deleteClient(@RequestParam int id) {
		return clientServices.deleteClient(id) ? "Success" : "failure";
	}
	
	
	@RequestMapping(value = "getClientList", method = RequestMethod.GET)
	@ResponseBody
	public List<ClientModel> getList(@RequestParam int offset, @RequestParam int index) {
		return clientServices.getList(index, offset);
	}
	
	@RequestMapping(value = "getClientNames", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getClientNames(@RequestParam String name) {
		return clientServices.getClientNames(name);
	}
	
	@RequestMapping(value = "getNamesList", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getNames() {
		return clientServices.getNames();
	}

}
