package org.shop.service;

import org.shop.model.ProductModel;
import org.shop.repository.ProductRepository;
import java.util.*;

public class ProductService {
	ProductRepository prodRepo= new ProductRepository();
	ArrayList al= prodRepo.getProductList();
	public int isAddProdcut(ProductModel pmodel) {
		
		boolean b=false;
		if(al!=null) {
			for(Object obj:al) {
				ProductModel model=(ProductModel)obj;
				if(model.getName().equals(pmodel.getName())) {
					b=true;
					break;
				}
			}
			if(b) {
				return 0;
			}
			else {
				b=prodRepo.isAddProduct(pmodel); 
				return b?1:-1;
				
			}
		}
		else {
			return -1;
		}
		
		
	}
	public ArrayList getProductList() {
		return prodRepo.getProductList();
	}
	
	public ProductModel searchProductModelById(int id) {
		return prodRepo.searchProductModelById(id);
	}
	
	public ProductModel searchProductModelByName(String name) {
		return prodRepo.searchProductModelByName(name);
	}
    
	public boolean chcekProductOrNot(String name) {
		boolean flag=false;
		for(Object obj:al) {
			ProductModel pmodel=(ProductModel)obj;
			if(name.equals(pmodel.getName())) {
			flag=true;
				break;
			}
			else {
				flag=false;
			}  
		}
		
		if(flag) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
}
