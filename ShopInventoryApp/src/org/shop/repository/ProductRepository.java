package org.shop.repository;

import org.shop.model.ProductModel;
import java.util.*;

public class ProductRepository {
	Scanner sc = new Scanner(System.in);
	ArrayList al = new ArrayList();

	public boolean isAddProduct(ProductModel pmodel) {
		boolean b = al.add(pmodel);
		if (b) {
			return true;

		} else {
			return false;
		}
		
		
	}
	public ArrayList getProductList() {
		return al.size()>=0?al:null;
	}
	
	public ProductModel searchProductModelById(int id) {
		boolean b=false;
		ProductModel model=null;
		for(Object obj:al) {
			model=(ProductModel)obj;
			if(model.getId()==id) {
				b=true;
				break;
			}
		}
		return b?model:null;
		
	}
  
	
	public ProductModel searchProductModelByName(String name) {
		boolean b=false;
		ProductModel model=null;
		for(Object obj:al) {
			model=(ProductModel)obj;
			if(model.getName().equals(name)) {
				b=true;
				break;
			}
		}
		return b?model:null;
		
	}
	
	


}
