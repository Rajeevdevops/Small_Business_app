package com.lws.services;

import java.util.List;

import com.lws.model.ProductModel;

public interface ProductServices {

	boolean saveProduct(ProductModel model);
	List<ProductModel> getList(int offset, int index);
	ProductModel getProductById(int id);
	List<String> getProductNames(String name);
	ProductModel getProductByName(String name);
	boolean deleteProduct(ProductModel model);
	boolean modifyProduct(ProductModel model);
	Long getCount();
	List<ProductModel> getProductNameId();
}
