package com.lws.services;

import java.util.List;

import com.lws.model.PurchaseItemModel;
import com.lws.model.PurchaseModel;

public interface PurchaseServices {

	boolean savePurchase(PurchaseModel model);
	List<PurchaseModel> getList(int offset, int index);
	Long getCount();
	List<PurchaseItemModel> getPurchaseItemList(int purchaseId, int offset, int index);
	Integer getPurchaseItemListCount(int id);
	boolean removePurchaseItem(PurchaseItemModel item, int purchaseId);
	boolean addPurchaseItem(PurchaseItemModel model, int purchaseId);
	PurchaseItemModel getPurchaseItem(int id) ;
	boolean updatePurchase(PurchaseModel model, int id) ;
	boolean deletePurchaseModel(int id);
}
