package org.shop.model;

public class ProductModel {
	private int id;
	private String name ;
	private int price;
	public ProductModel () {
		
	}
	public ProductModel(String name,int id,int price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
