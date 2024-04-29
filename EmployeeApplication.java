package vaibhav;

import java.sql.*;
import java.util.*;

public class EmployeeApplication {
	public static void main(String[] args) throws SQLException {
		com.mysql.cj.jdbc.Driver d = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "vaibhav");
		Scanner sc = new Scanner(System.in);
		if (conn != null) {
			Statement smt = conn.createStatement();
			System.out.println("database connected successfully..........");
			int choice;
			do {
				System.out.println("1:insert data in Database");
				System.out.println("2:Displey all Records in Database table");
				System.out.println("3:Update Employee record by id");
				System.out.println("4:Delete Employee Record by Name");
				System.out.println("Enter your choice");
				choice = sc.nextInt();

				switch (choice) {

				case 1:
					System.out.println("Enter the id name email salary and contact:");
					int id = sc.nextInt();
					sc.nextLine();
					String name = sc.nextLine();
					String email = sc.nextLine();
					String salary = sc.nextLine();
					String contact = sc.nextLine();
					int value = smt.executeUpdate("insert into Employee values('" + id + "','" + name + "','" + email+ "','"+salary+"','"+contact+"')");
					if (value == 1)
						System.out.println("data inserted....");
					else
						System.out.println("data not inserted.....");
					break;

				case 2:
					ResultSet rs=smt.executeQuery("select *from Employee");
					while(rs.next()) {
						int eid=rs.getInt(1);
						String ename=rs.getString(2);
						String eemail=rs.getString(3);
						String esalary=rs.getString(4);
						String econtact=rs.getString(5);
						System.out.println(eid+"\t"+ename+"\t"+eemail+"\t"+esalary+"\t"+econtact);
					}
					
					System.out.println("\n========================================================\n");
					
					break;
					
				case 3:
					System.out.println("Entet the id to update data in database:");
					int did=sc.nextInt();
					System.out.println("Enter the new salary:");
					int sal=sc.nextInt();
					int value1 = smt.executeUpdate("update Employee set salary='"+sal+"' where id="+did);
					if(value1!=0) {
						System.out.println("data updated ..............");
					}
					else {
						System.out.println("data not updated.........");
					}
					
					break;
					
				case 4:
					System.out.println("Entet the name to delete data in database:");
					sc.nextLine();
					String dname=sc.nextLine();
					int value2 = smt.executeUpdate("delete from Employee where name='"+dname+"'");
					if(value2!=0) {
						System.out.println("data deleted ..............");
					}
					else {
						System.out.println("data not deleted.........");
					}
					
				}
				
				
					
				
				
					
				

			} while (choice <5);
		} else {
			System.out.println("not connected");
		}

	}

}
