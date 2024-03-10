package org.shop.client;

import java.util.ArrayList;
import java.util.Scanner;

import org.shop.model.CustomerModel;
import org.shop.model.OrderModel;
import org.shop.model.ProductModel;
import org.shop.service.OrderService;
import org.shop.service.ProductService;

public class ShopClientApplication {

	public static void main(String[] args) {
		ProductService prodService = new ProductService();
		Scanner sc = new Scanner(System.in);
		OrderService oservice = new OrderService();
		String cname[] = new String[10];
		String newcname[] = new String[10];
		do {
			System.out.println("1:Add new product");
			System.out.println("2:Display All Products");
			System.out.println("3:Search Product by Name or Id or Price ");
			System.out.println("4:Add New Order ");
			System.out.println("5:View All Orders  ");
			System.out.println("6:View Repeated Customers");
			System.out.println("7:View Profit Date wise  ");
			System.out.println("8:Give Discount the Repeated Customers.");
			System.out.println("Enter your choice:");
			System.out.println("=========================================================================");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter product name id and price ");
				sc.nextLine();
				String name = sc.nextLine();
				int id = sc.nextInt();
				int price = sc.nextInt();
				ProductModel pmodel = new ProductModel(name, id, price);
				int b = prodService.isAddProdcut(pmodel);
				if (b == 1) {
					System.out.println("=========================================================================");
					System.out.println("Product Add successfully...........");
					System.out.println("=========================================================================");
				} else if (b == -1) {
					System.out.println("=========================================================================");
					System.out.println("There is no data found.........");
					System.out.println("=========================================================================");
				} else {
					System.out.println("=========================================================================");
					System.out.println("Alredy Product in Collection...........");
					System.out.println("=========================================================================");
				}
				break;

			case 2:
				System.out.println("=========================================================================");
				ArrayList al = prodService.getProductList();
				for (Object obj : al) {
					ProductModel model = (ProductModel) obj;
					System.out.println(model.getId() + "\t" + model.getName() + "\t" + model.getPrice());
				}
				System.out.println("=========================================================================");
				break;

			case 3:
				System.out.println("=========================================================================");
				System.out.println("Enter the choice:");
				System.out.println("1:id");
				System.out.println("2:name");
				System.out.println("=========================================================================");
				int choice1 = sc.nextInt();
				switch (choice1) {

				case 1:
					System.out.println("=========================================================================");
					System.out.println("Enter the product id:");
					int pid = sc.nextInt();
					ProductModel model = prodService.searchProductModelById(pid);
					if (model != null) {
						System.out.println(model.getId() + "\t" + model.getName() + "\t" + model.getPrice());
						System.out.println("=========================================================================");

					} else {
						System.out.println("=========================================================================");
						System.out.println("Product not found");
						System.out.println("=========================================================================");
					}

					break;
				case 2:
					System.out.println("=========================================================================");
					System.out.println("Enter the product name:");
					sc.nextLine();
					String pname = sc.nextLine();
					ProductModel model2 = prodService.searchProductModelByName(pname);
					if (model2 != null) {
						System.out.println(model2.getId() + "\t" + model2.getName() + "\t" + model2.getPrice());
						System.out.println("=========================================================================");

					} else {
						System.out.println("=========================================================================");
						System.out.println("Product not found");
						System.out.println("=========================================================================");
					}

					break;
				default:
					System.out.println("=========================================================================");
					System.out.println("wrong choice");
					System.out.println("=========================================================================");
				}
				break;
			case 4:
				System.out.println("=========================================================================");
				System.out.println("Enter Order Details:");
				System.out.println("Enter customer Details id name contact and date");
				sc.nextLine();
				id = sc.nextInt();
				sc.nextLine();
				name = sc.nextLine();
				String contact = sc.nextLine();
				String date = sc.nextLine();
				CustomerModel c = new CustomerModel(id, name, contact, date);
				System.out.println("Enter the count");
				int count = sc.nextInt();
				ProductModel p[] = new ProductModel[count];

				int qty[] = new int[count];
				int flag = 0;
				for (int i = 0; i < p.length; i++) {
					p[i] = new ProductModel();
					sc.nextLine();
					System.out.println("Enter the product details like as Name");
					name = sc.nextLine();
					boolean g = prodService.chcekProductOrNot(name);
					if (!g) {
						flag = 1;
						break;
					} else {
						System.out.println("=========================================================================");
						System.out.println("Product is available");
						System.out.println("=========================================================================");
						System.out.println("Enter the product correct id price and quentity");
						id = sc.nextInt();
						price = sc.nextInt();
						int quentity = sc.nextInt();
						p[i].setId(id);
						p[i].setName(name);
						p[i].setPrice(price);
						qty[i] = quentity;

					}

				}
				if (flag == 0) {

					OrderModel omodel = new OrderModel();
					omodel.setCustModedl(c);
					omodel.setProductModel(p);
					omodel.setQuentity(qty);

					boolean check = oservice.isOrder(omodel);
					if (check) {
						System.out.println("=========================================================================");
						System.out.println("Order Place successfully........");
						System.out.println("=========================================================================");
					}

				} else {
					System.out.println("=========================================================================");
					System.out.println("Product is not available");
					System.out.println("=========================================================================");
				}

				break;
			case 5:
				al = oservice.getOrgerList();
				for (Object obj : al) {
					OrderModel o = (OrderModel) obj;
					CustomerModel cmodel = o.getCustModedl();
					ProductModel prods[] = o.getProducts();
					int quantity[] = o.getQuentity();
					System.out.println("=====================================================================");
					System.out.println(cmodel.getName() + "\t" + cmodel.getId() + "\t" + cmodel.getContact());
					int grantTotal = 0;
					for (int i = 0; i < prods.length; i++) {
						int total = quantity[i] * prods[i].getPrice();
						grantTotal = grantTotal + total;
						System.out.println(prods[i].getId() + "\t" + prods[i].getName() + "\t" + prods[i].getPrice()
								+ "\t" + quantity[i]);
					}

					System.out.println("Grant Total is :" + grantTotal);
				}
				System.out.println("=========================================================================");
				break;

			case 6:
				al = oservice.getOrgerList();

				int k = 0;
				for (Object obj : al) {
					OrderModel o = (OrderModel) obj;
					CustomerModel cmodel1 = o.getCustModedl();
					cname[k] = cmodel1.getName();
					k++;
				}

				for (int i = 0; i < cname.length; i++) {
					for (int j = i + 1; j < cname.length; j++) {
						if (cname[i] != null && cname[j] != null && cname[i].equals(cname[j])) {
							System.out.println(cname[i]);
							newcname[i] = cname[i];
							break;
						}
					}
				}

				System.out.println("=========================================================================");
				break;

			case 7:
				System.out.println("Enter the date:");
				sc.nextLine();
				String date1 = sc.nextLine();
				al = oservice.getOrgerList();
				for (Object obj : al) {
					OrderModel o = (OrderModel) obj;
					CustomerModel cmodel = o.getCustModedl();
					if (cmodel.getDate().equals(date1)) {
						ProductModel prods[] = o.getProducts();
						int quantity[] = o.getQuentity();
						System.out.println(cmodel.getName() + "\t" + cmodel.getId() + "\t" + cmodel.getContact());
						System.out.println("=====================================================================");
						int grantTotal = 0;
						for (int i = 0; i < prods.length; i++) {
							int total = quantity[i] * prods[i].getPrice();
							grantTotal = grantTotal + total;
							System.out.println(prods[i].getId() + "\t" + prods[i].getName() + "\t" + prods[i].getPrice()
									+ "\t" + quantity[i]);
						}
						System.out.println("=========================================================================");
						System.out.println("Total price :" + grantTotal);
						int profit = (grantTotal * 15) / 100;
						System.out.println("Profit :" + profit);
						System.out.println("=========================================================================");

					}
				}

				break;

			case 8:

				al = oservice.getOrgerList();

				k = 0;
				for (Object obj : al) {
					OrderModel o = (OrderModel) obj;
					CustomerModel cmodel1 = o.getCustModedl();
					cname[k] = cmodel1.getName();
					k++;
				}

				for (int i = 0; i < cname.length; i++) {
					for (int j = i + 1; j < cname.length; j++) {
						if (cname[i] != null && cname[j] != null && cname[i].equals(cname[j])) {
							// System.out.println(cname[i]);
							newcname[i] = cname[i];
							break;
						}
					}
				}

				int e = 0;
				for (Object obj : al) {
					OrderModel o = (OrderModel) obj;
					CustomerModel cmodel = o.getCustModedl();
					if (cmodel.getName().equals(newcname[e])) {
						ProductModel prods[] = o.getProducts();
						int quantity[] = o.getQuentity();
						System.out.println(cmodel.getName() + "\t" + cmodel.getId() + "\t" + cmodel.getContact());
						System.out.println("=====================================================================");
						int grantTotal = 0;
						for (int i = 0; i < prods.length; i++) {
							int total = quantity[i] * prods[i].getPrice();
							grantTotal = grantTotal + total;
							System.out.println(prods[i].getId() + "\t" + prods[i].getName() + "\t" + prods[i].getPrice()
									+ "\t" + quantity[i]);
						}
						System.out.println("=========================================================================");
						System.out.println("Total price :" + grantTotal);
						int discount = (grantTotal * 10) / 100;
						System.out.println("Discount :" + discount);
						System.out.println("Discount price:" + (grantTotal - discount));
						System.out.println("=========================================================================");

					}
					e++;

				}

				break;
			default:
				System.out.println("Invaid choice");
			}

		} while (true);

	}

}
