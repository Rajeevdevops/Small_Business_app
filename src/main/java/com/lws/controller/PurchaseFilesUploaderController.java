package com.lws.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lws.model.PurchaseFilesModel;

@Controller
public class PurchaseFilesUploaderController {
	
	@RequestMapping(value = "purchaseOrderFiles", method = RequestMethod.GET)
	public ModelAndView getPurchaseOrderFiles(@RequestParam int id) {
		
		ModelAndView model = new ModelAndView("purchaseFilesUpload");
		PurchaseFilesModel purchaseFiles = new PurchaseFilesModel();
		purchaseFiles.setFileName("purchaseOrder-"+id);
		model.addObject("purchase", purchaseFiles);
		String directoryPath = purchaseFiles.getDirectory() + "/"+purchaseFiles.getFileName();
		File directory = new File(directoryPath);
		List<String> list = new ArrayList<String>();
		if(directory.isDirectory()) {
			for(File file : directory.listFiles()) {
				list.add(file.getName());
			}
		}
		
		model.addObject("files", list);
		return model;
	}
	
	@RequestMapping(value = "downloadPurchaseOrder",method = RequestMethod.GET)
	public void downloadPurchaseOrder(HttpServletRequest request, HttpServletResponse response) {
		String directory = new PurchaseFilesModel().getDirectory()+"/"+request.getParameter("path")+"/"+request.getParameter("name");
		try {
			File file = new File(directory);
			ServletContext context = request.getSession().getServletContext();
			String mimeType  = context.getMimeType(directory);
			if(mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int)file.length());

	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
	        response.setHeader(headerKey, headerValue);

	        FileInputStream inputStream = new FileInputStream(file);
	        OutputStream outputStream = response.getOutputStream();
	        byte[] buffer = new byte[4096];

	        int bytesRead = -1;

	        //While there are bytes to write
	        while((bytesRead = inputStream.read(buffer)) != -1)
	        {
	            outputStream.write(buffer, 0, bytesRead);
	        }

	        //Close the streams
	        inputStream.close();
	        outputStream.close();

	        

			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	@RequestMapping(value = "purchaseOrderFiles", method = RequestMethod.POST)
	public ModelAndView uploadFiles(@RequestParam String name, @RequestParam MultipartFile file) {
		PurchaseFilesModel purchaseFiles = new PurchaseFilesModel();
		ModelAndView model = new ModelAndView("purchaseFilesUpload");
		boolean flag = false;
		try {
			String path = purchaseFiles.getDirectory()+"/"+name;
			File directory = new File(path);
			if(!directory.isDirectory()) {
				directory.mkdirs();
			}
			
			File uploadFile = new File(path + File.separator +file.getOriginalFilename());
			if(!uploadFile.exists()) {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile)) ;
				stream.write(file.getBytes());
				stream.close();
				flag = true;
				
			} else {
				path += File.separator + (System.currentTimeMillis()%100000+"-"+file.getOriginalFilename());
				File newFile = new File(path);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
				stream.write(file.getBytes());
				stream.close();
				flag = true;
			}
			
			model.addObject("message", flag ? "Success" : "failure"); 
		} catch (Exception ex) {
			flag = false;
			System.out.println(ex.getMessage());
		}
		purchaseFiles.setFileName(name);
		String directoryPath = purchaseFiles.getDirectory() + "/"+purchaseFiles.getFileName();
		File directory = new File(directoryPath);
		List<String> list = new ArrayList<String>();
		if(directory.isDirectory()) {
			for(File f : directory.listFiles()) {
				list.add(f.getName());
			}
		}
		
		model.addObject("files", list);
		model.addObject("purchase", purchaseFiles);
		return model;
	}
	
	
	

}
