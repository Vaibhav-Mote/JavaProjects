package org.shop.model;

public class OrderModel {

	private CustomerModel custModedl;
	private ProductModel pmodel[];
	private int quantity[];

	public CustomerModel getCustModedl() {
		return custModedl;
	}
	public ProductModel[] getProducts() {
		return pmodel;
		
	}
	public int []getQuentity() {
		return this.quantity;
		
	}

	public void setCustModedl(CustomerModel custModedl) {
		this.custModedl = custModedl;
	}
	public void setProductModel(ProductModel pmodel[]) {
		this.pmodel=pmodel;
	}
	public void setQuentity(int quantity[]) {
		this.quantity=quantity;
	}
	
	public void calBill() {
		System.out.println(custModedl.getId()+"\t"+custModedl.getName());
		System.out.println("==========================================================");
		int grantTotal=0;
		for(int i=0;i<pmodel.length;i++) {
			int id=pmodel[i].getId();
			String name=pmodel[i].getName();
			int price=pmodel[i].getPrice();
			int total=price*quantity[i];
			grantTotal=grantTotal+total;
			System.out.println(id+"\t"+price+"\t"+name);
			
			
		}
		System.out.println("Total order bill is:"+grantTotal);
	}
	
	
	
}
