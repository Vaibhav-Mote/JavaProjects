package vaibhav;
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class BankApplication {
	public static void main(String[] args) throws SQLException {
		com.mysql.cj.jdbc.Driver d = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc2", "root", "vaibhav");
		Scanner sc = new Scanner(System.in);
		PreparedStatement pst;
		ResultSet rs;
		int Transition_id = 500;
		int choice;
		if (conn != null) {
			System.out.println("connection successfully..........");

			do {

				System.out.println("\n1:Add Bank Customer");
				System.out.println("2:View all Customer");
				System.out.println("3:Update customer");
				System.out.println("4:Delete Customer");
				System.out.println("5:Deposite Money");
				System.out.println("6:Withdraw Money");
				System.out.println("7:View Statements");
				System.out.println("8:Check Balance");
				System.out.println("Enter your choice:");
				choice = sc.nextInt();
				switch (choice) {
				case 1:

					System.out.println("100:SBI");
					System.out.println("101:RBI");
					System.out.println("102:HDFC");
					System.out.println("103:IPPB");
					System.out.println("choice your bank:");
					int choice2 = sc.nextInt();
//					Statement smt=conn.createStatement();
//					rs=smt.executeQuery("select bank_id from bank where bank_id="+choice2);
					pst = conn.prepareStatement("select bank_id from bank where bank_id=?");
					pst.setInt(1, choice2);
					ResultSet rs1 = pst.executeQuery();
					if (rs1.next()) {
						int bankid = rs1.getInt(1);
						if (choice2 == bankid) {

							System.out.println("\n================Bank is avaliable======================\n");
							System.out.println("Enter the Name:");
							sc.nextLine();
							String Name = sc.next();
							System.out.println("Enter the Email");
							String Email = sc.next();
							System.out.println("Enter the Contact");
							String Contact = sc.next();
							System.out.println("Enter the Address:");
							String Address = sc.next();
							String Balance = "1000";
							pst = conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
							pst.setInt(1, 0);
							pst.setString(2, Name);
							pst.setString(3, Email);
							pst.setString(4, Contact);
							pst.setString(5, Address);
							pst.setString(6, Balance);
							int value = pst.executeUpdate();
							PreparedStatement pst1;
							pst1 = conn.prepareStatement("select Acno from Customer where Email=?");
							pst1.setString(1, Email);
							rs1 = pst1.executeQuery();
							if (rs1.next()) {
								int Acno = rs1.getInt(1);
								PreparedStatement pst3 = conn.prepareStatement("insert into cbt_join values(?,?,?)");
								pst3.setInt(1, Acno);
								pst3.setInt(2, bankid);
								pst3.setString(3, null);
								int value2 = pst3.executeUpdate();
								if ((value == 1) && (value2 == 1))
									System.out.println("Data inserted Successfully....");
								else
									System.out.println("data not inserted successfully....");

							}
						} else {
							System.out.println("some problem ....");
						}

					} else {
						System.out.println("\n=======================bank not avaliable=====================\n");
					}

					break;

				case 2:
					pst = conn.prepareStatement("select *From customer");
					rs = pst.executeQuery();

					ResultSetMetaData metaData = pst.getMetaData();
					int columnCount = metaData.getColumnCount();
					for (int i = 1; i <= columnCount; i++) {
						System.out.print(metaData.getColumnName(i) + "\t\t");
					}

					System.out.println();
					while (rs.next()) {
						System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t"
								+ rs.getString(4) + "\t\t" + rs.getString(5) + "\t\t" + rs.getString(6));
					}
					System.out.println(
							"=========================================================================================");
					break;

				case 3:
					System.out.println("Enter Acno to update Coustomer: ");
					int Acno = sc.nextInt();
					System.out.println("Enter the new Name:");
					String Name = sc.next();
					System.out.println("Enter the new Email");
					String Email = sc.next();
					System.out.println("Enter the new Contact");
					String Contact = sc.next();
					System.out.println("Enter the new  Address:");
					String Address = sc.next();
					String Balance = "1000";
					pst = conn.prepareStatement(
							"update customer set name=?,Email=?,Contact=?,Address=?,Balance=? where Acno=" + Acno);
					pst.setString(1, Name);
					pst.setString(2, Email);
					pst.setString(3, Contact);
					pst.setString(4, Address);
					pst.setString(5, Balance);
					int value = pst.executeUpdate();
					if (value != 0)
						System.out.println("Data updated successfully");
					else
						System.out.println("Data not Updated Successfully");
					break;

				case 4:
					System.out.println("Enter the Acno to delete Customer:");
					Acno = sc.nextInt();
					pst = conn.prepareStatement("delete from customer where Acno=" + Acno);
					value = pst.executeUpdate();
					if (value == 1)
						System.out.println("Data Deleted successfully");
					else
						System.out.println("Data not Deleted Successfully");
					break;

				case 5:
					System.out.println("Enter the Acno to Deposite Balance: ");
					Acno = sc.nextInt();
					System.out.println("Enter the Amount:");
					String Amount = sc.next();
					pst = conn.prepareStatement("select Balance from customer where Acno=" + Acno);
					rs = pst.executeQuery();
					rs.next();
					Balance = rs.getString(1);
					pst = conn.prepareStatement("update customer set Balance=? where Acno=" + Acno);

					int IntBalance = Integer.parseInt(Balance) + Integer.parseInt(Amount);
					String StringBalance = String.valueOf(IntBalance);
					pst.setString(1, StringBalance);
					value = pst.executeUpdate();

					PreparedStatement pst3 = conn
							.prepareStatement("select T_id ,bank_id from cbt_join where Acno=" + Acno);
					ResultSet rs2 = pst3.executeQuery();
					rs2.next();
					String trans_id = rs2.getString(1);
					int bank_id = rs2.getInt(2);
					int value2 = 0;
					if (trans_id == null) {

						PreparedStatement pst1 = conn.prepareStatement("update cbt_join set T_id=? where Acno=?");
						pst1.setInt(1, 1);
						pst1.setInt(2, Acno);
						value2 = pst1.executeUpdate();
					} else {
						PreparedStatement pst1 = conn.prepareStatement("insert into cbt_join values(?,?,?)");
						pst1.setInt(1, Acno);
						pst1.setInt(2, bank_id);
						int transition_id = Integer.parseInt(trans_id);
						pst1.setInt(3, 1);
						value2 = pst1.executeUpdate();
					}

					if ((value == 1) && (value2 == 1))
						System.out.println("Deposite Amount Successfully...");
					else
						System.out.println("Transition failed....");
					System.out.println("============================================================");
					break;

				case 6:
					System.out.println("Enter the Acno to Withdraw Balance: ");
					Acno = sc.nextInt();
					System.out.println("Enter the Amount:");
					Amount = sc.next();
					pst = conn.prepareStatement("select Balance from customer where Acno=" + Acno);
					rs = pst.executeQuery();
					rs.next();
					Balance = rs.getString(1);
					if (Integer.parseInt(Balance) != 0) {
						pst = conn.prepareStatement("update customer set Balance=? where Acno=" + Acno);

						IntBalance = Integer.parseInt(Balance) - Integer.parseInt(Amount);
						StringBalance = String.valueOf(IntBalance);
						pst.setString(1, StringBalance);
						value = pst.executeUpdate();

						pst3 = conn.prepareStatement("select T_id ,bank_id from cbt_join where Acno=" + Acno);
						rs2 = pst3.executeQuery();
						rs2.next();
						trans_id = rs2.getString(1);
						bank_id = rs2.getInt(2);
						value2 = 0;

						System.out.println(trans_id);
						if (trans_id == null) {

							PreparedStatement pst1 = conn.prepareStatement("update cbt_join set T_id=? where Acno=?");
							pst1.setInt(1, 2);
							pst1.setInt(2, Acno);
							value2 = pst1.executeUpdate();
						} else {
							PreparedStatement pst1 = conn.prepareStatement("insert into cbt_join values(?,?,?)");
							pst1.setInt(1, Acno);
							pst1.setInt(2, bank_id);
							int transition_id = Integer.parseInt(trans_id);
							pst1.setInt(3, transition_id);
							value2 = pst1.executeUpdate();
						}
						if ((value == 1) && (value2 == 1))
							System.out.println("withdraw  Amount Successfully...");
						else
							System.out.println("Transition failed....");
						System.out.println("============================================================");

					} else {
						System.out.println("Insufficient fund.........");
					}

					break;

				case 7:
					pst = conn.prepareStatement(
							"select c.* ,cbt.bank_id,cbt.t_id,b.bank_name from  customer c inner join cbt_join cbt on c.Acno=cbt.Acno inner join bank b on b.bank_id=cbt.bank_id inner join Transaction t on t.t_id=cbt.t_id");
					rs = pst.executeQuery();
					while (rs.next()) {
						Acno = rs.getInt(1);
						Name = rs.getString(2);
						Email = rs.getString(3);
						Contact = rs.getString(4);
						Address = rs.getString(5);
						Balance = rs.getString(6);
						bank_id = rs.getInt(7);
						trans_id = rs.getString(8);
						// String Transaction_name = rs.getString(9);
						String bank_name = rs.getString(9);

						// Date date = rs.getDate(9);
						System.out.println("===============Mini Statement=================");
						System.out.println("Acno:" + Acno);
						System.out.println("Name:" + Name);
						System.out.println("Email:" + Email);
						System.out.println("Contact:" + Contact);
						System.out.println("Bank_id:" + bank_id);
						System.out.println("Bank name:" + bank_name);
						System.out.println("Balance:" + Balance);
						System.out.println("Address:" + Address);

						System.out.println("=============================================");

					}
					break;
				case 8:
					System.out.println("Enter Acno to Check Balance:");
					Acno = sc.nextInt();
					pst = conn.prepareStatement("select Balance from customer where Acno=?");
					pst.setInt(1, Acno);
					rs = pst.executeQuery();
					String bal = null;
					while (rs.next()) {
						bal = rs.getString(1);
					}
					System.out.println("Available Balance:" + bal);
				}

			} while (choice != 9);
		} else {
			System.out.println("connection failed........");

		}

	}

}
