package org.shop.model;

public class CustomerModel {
private int id;
private String name;
private String contact;
private String date;
public CustomerModel() {
	
}
public CustomerModel(int id ,String name,String contact,String date) {
	this.id=id;
	this.name=name;
	this.contact=contact;
	this.date=date;
	
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
public String getContact() {
	return contact;
}
public void setdate(String date) {
	this.date=date;
}
public String getDate() {
	return date;
}
public void setContact(String contacat) {
	this.contact = contact;
}

}
