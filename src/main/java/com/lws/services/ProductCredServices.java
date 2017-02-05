package com.lws.services;

public interface ProductCredServices {
	int getPurchaseQuantity(int productId);
	int getProductQuantity(int productId);
	float getUnitRate(int productId);
	int getInvoiceQuantity(int productId);
	
}
