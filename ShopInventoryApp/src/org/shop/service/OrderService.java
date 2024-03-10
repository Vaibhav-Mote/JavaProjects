package org.shop.service;

import java.util.ArrayList;

import org.shop.model.OrderModel;
import org.shop.repository.OrderRepository;

public class OrderService {
	OrderRepository orderRepo=new OrderRepository();
	public boolean isOrder(OrderModel model) {
		return orderRepo.isAddOrder(model);
		
	}
	public ArrayList getOrgerList() {
		 return orderRepo.getOrderList();
	}

}
