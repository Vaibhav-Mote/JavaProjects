package org.shop.repository;

import java.util.ArrayList;

import org.shop.model.OrderModel;

public class OrderRepository {
	ArrayList orderList=new ArrayList();
	public boolean isAddOrder(OrderModel omodel) {
		boolean b=orderList.add(omodel);
		if(b) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	public ArrayList getOrderList() {
		return orderList;
	}

}
