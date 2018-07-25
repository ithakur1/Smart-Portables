

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import java.sql.*;

public class MySqlDataStoreUtilities {
	
	Connection conn = null;
	HashMap<String, User> users = null;
	HashMap<String, ProductCatalog> hmPrdCat = new HashMap<String, ProductCatalog>();
	User user = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ProductCatalog pc = null;
	Statement stmt = null;
	PrintWriter out;
	Product prod = null;



public void insertUserDetails(User user)throws SQLException
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
		String insert = "INSERT INTO users(firstName,lastName,age,gender,country,address,city,state,userID,password,userType,zipcode) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		
		pst = conn.prepareStatement(insert);
		
		pst.setString(1,user.getFirstName());
		pst.setString(2,user.getLastName());
		pst.setString(3,user.getAge());
		pst.setString(4,user.getGender());
		pst.setString(5,user.getCountry());
		pst.setString(6,user.getAddress());
		pst.setString(7,user.getCity());
		pst.setString(8,user.getState());
		pst.setString(9,user.getUserID());
		pst.setString(10,user.getPassword());
		pst.setString(11,user.getUserType());
		pst.setString(12,user.getZipcode());
	
		pst.execute();
		
		pst.close();
		conn.close();
	}
	catch(SQLException se)
	{	//Handle errors for JDBC
		se.printStackTrace();
	}
	catch(Exception e)
	{	//Handle errors for Class.forName
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pst!=null)
            pst.close();
		}
		catch(SQLException se2)
		{
			
		}
		
		try
		{
			if(conn!=null)
            conn.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}
public HashMap<String, User> viewUserDetails() throws SQLException
{
	try
	{
		user = new User();
		users = new HashMap<String, User>();
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
		stmt = conn.createStatement();			
		String view = "SELECT * FROM users";
		rs = stmt.executeQuery(view);
		
		while(rs.next())
		{
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setAge(rs.getString("age"));
			user.setGender(rs.getString("gender"));
			user.setCountry(rs.getString("country"));
			user.setCity(rs.getString("city"));
			user.setState(rs.getString("state"));
			user.setUserID(rs.getString("userID"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getString("userType"));
			user.setZipcode(rs.getString("zipcode"));
			
			users.put(rs.getString("userID"), user);
		}
		rs.close();
		stmt.close();
		conn.close();
	}
	catch(SQLException se)
	{	//Handle errors for JDBC
		se.printStackTrace();
	}
	catch(Exception e)
	{	//Handle errors for Class.forName
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(stmt!=null)
            stmt.close();
		}
		catch(SQLException se2)
		{
			
		}
		
		try
		{
			if(conn!=null)
            conn.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	return users;		
}

/*INSERT INTO `ewadb`.`customerorder`
(`orderID`,
`userName`,
`productName`,
`price`,
`retailer`,
`creditCardNo`,
`itemIds`)
VALUES
(<{orderID: }>,
<{userName: }>,
<{productName: }>,
<{price: }>,
<{retailer: }>,
<{creditCardNo: }>,
<{itemIds: }>);
*/

	//to insert orders into the db
	public int insertOrders(String ccNum){
		String userName = "";
		String ccNo = "";
		
		String productName = "";
		String price = "";
		String retailer = "";
		String itemIds = "";
		String date="";
		
		ccNo = !ccNum.isEmpty() ? ccNum : "";
	//	userName = !uName.isEmpty() ? uName : "";
		
		if(!BuyHashMap.orders.isEmpty() && BuyHashMap.orders.size() > 0){
			 HashMap<String, ArrayList<ItemOrdered>> ordersObject = BuyHashMap.orders;
			 ArrayList<ItemOrdered> items = null;
	
			 //iterate through the hashmap
			 for (Map.Entry<String, ArrayList<ItemOrdered>> entry : ordersObject.entrySet()) {
				//we have some orders. We have a username
			    userName = entry.getKey();
			    items = entry.getValue();
	//		    Object value = entry.getValue();
			}
			 int totPrice = 0;
			 
			 //forming concatenated strings of names
			 for(int i=0; i<items.size(); i++){
				 productName += items.get(i).getName() + ",";
				 price += items.get(i).getPrice() + ",";
				 retailer += items.get(i).getRetailer() + ",";
				 itemIds += items.get(i).getItemId() + ",";
			 }
		}
		
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			String insert = "INSERT INTO customerorder(userName,productName,price,retailer,creditCardNo,itemIds,date) " 
							+ "VALUES (?,?,?,?,?,?,?);";
			
			pst = conn.prepareStatement(insert);
			
			pst.setString(1,userName);
			pst.setString(2,productName);
			pst.setString(3,price);
			pst.setString(4,retailer);
			pst.setString(5,ccNo);
			pst.setString(6,itemIds);
			pst.setString(7,date);
			pst.execute();
			
			pst.close();
			conn.close();
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pst!=null)
	            pst.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		
		
		
		return 0;
	}
	
	public ArrayList<ItemOrdered>  fetchOrders(String userName){
		
		ArrayList<ItemOrdered> iord = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "SELECT * FROM customerorder where userName = '" + userName + "'";
			rs = stmt.executeQuery(view);
			
			
			String[] prNames = null;
			String[] prices = null;
			String[] retailers = null;
			String ccNum = null;
			String[] itemIDs = null;
					
			iord = new ArrayList<ItemOrdered>();
			ItemOrdered ioItem = null;
			while(rs.next())
			{
				//userName,productName,price,retailer,creditCardNo,itemIds
				 
				 prNames = rs.getString("productName").split(",");
				 prices = rs.getString("price").split(",");
				 retailers = rs.getString("retailer").split(",");
				 ccNum = rs.getString("creditCardNo");
				 itemIDs = rs.getString("itemIds").split(",");
				 
				 for(int i=0; i<prNames.length; i++)
				 {
					 ioItem = new ItemOrdered(itemIDs[i].trim(), prNames[i].trim(), prices[i].trim(), ccNum, retailers[i].trim());
					 iord.add(ioItem);
				 }
				
			}
			
			 
			 
			rs.close();
			stmt.close();
			conn.close();
			return iord;
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return iord;
 	
	}
	public void insertProdDetails(Product prod)throws SQLException {
		System.out.println(prod.getProdName());
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			//String insert = "INSERT INTO products VALUES (?,?,?,?,?,?,?);";
			
			//String insert = "INSERT INTO products(prodID,prodName,prodPrice,prodType,quantity,retailer,manufacturerRebate,condition) " + "VALUES (?,?,?,?,?,?,?,?);";
			pst = conn.prepareStatement("insert into products values(?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1,prod.getProdID());
			pst.setString(2,prod.getProdName());
			pst.setString(3,prod.getProdPrice());
			pst.setString(4,prod.getProdType());
			pst.setString(5,prod.getQuantity());
			pst.setString(6,prod.getRetailer());
			pst.setString(7,prod.getManufacturerRebate());
			pst.setString(8,prod.getCondition());
			pst.setString(9,prod.getOnSale());
			pst.setString(10,prod.getImage());
			
		System.out.println(prod.getProdName());
			pst.execute();
			System.out.println(prod.getManufacturerRebate());
			pst.close();
			conn.close();
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pst!=null)
	            pst.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	public ArrayList<Product> viewProductsDetails() {
		ArrayList<Product> prods = new ArrayList<Product>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "SELECT * FROM products";
			rs = stmt.executeQuery(view);
			
			while(rs.next()){
				Product prod = new Product(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				prods.add(prod);
			}
			
	
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return prods;
	}
	public ArrayList<Product> viewProductManuRebatesDetails() {
		ArrayList<Product> prods = new ArrayList<Product>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "SELECT * FROM products where manufacturerRebate ='Yes'";
			rs = stmt.executeQuery(view);
			
			while(rs.next()){
				Product prod = new Product(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				prods.add(prod);
			}
			
	
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return prods;
	}
	public ArrayList<Product> viewProductsOnSale() {
		ArrayList<Product> prods = new ArrayList<Product>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "SELECT * FROM products where onSale = 'Yes'";
			rs = stmt.executeQuery(view);
			
			while(rs.next()){
				Product prod = new Product(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				prods.add(prod);
			}
			
	
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return prods;
	}
	public ArrayList<ItemOrdered> viewProductOrderedDetails() {
		ArrayList<ItemOrdered> iord = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "select productName, price, count(productName) as countProd, (count(productName)*price) as totalSales  from customerorder " +
							"group by productName ";
			rs = stmt.executeQuery(view);
			
			
			String prNames = null;
			String prices = null;
			String countProd = null;
			String totalSales = null;
			String itemIDs = null;
					
			iord = new ArrayList<ItemOrdered>();
			ItemOrdered ioItem = null;
			while(rs.next())
			{
				//userName,productName,price,retailer,creditCardNo,itemIds
				//productName, price, count(productName) as CountProd
				 prNames = rs.getString("productName");
				 prices = rs.getString("price");
				 countProd = rs.getString("CountProd");
				 totalSales = rs.getString("totalSales");
				 ioItem = new ItemOrdered(" ", prNames, prices,totalSales, countProd);
				 iord.add(ioItem);
			}
			
			 
			 
			rs.close();
			stmt.close();
			conn.close();
			return iord;
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return iord;
	}
	public ArrayList<ItemOrdered> viewProductsOrderedByDate() {
		ArrayList<ItemOrdered> iord = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "select date as dtt, count(date) as dateCount, sum(price) as totalSales from customerorder group by date";
			rs = stmt.executeQuery(view);
			
			
			String ddt = null;
			String dateCount = null;
			String totalSales = null;
			
					
			iord = new ArrayList<ItemOrdered>();
			ItemOrdered ioItem = null;
			while(rs.next())
			{
				//userName,productName,price,retailer,creditCardNo,itemIds
				 
				ddt = rs.getString("dtt");
				 dateCount = Integer.toString(rs.getInt("dateCount"));
				 totalSales = Integer.toString(rs.getInt("totalSales"));
				 
				 
				
					 ioItem = new ItemOrdered(ddt,dateCount,totalSales,null,null);
					 iord.add(ioItem);
				 
				
			}
			
			 
			 
			rs.close();
			stmt.close();
			conn.close();
			return iord;
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return iord;
	}
	public ArrayList<Product> viewProductsBarChart() {
		ArrayList<Product> prods = new ArrayList<Product>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "SELECT * FROM products";
			rs = stmt.executeQuery(view);
			
			while(rs.next()){
				Product prod = new Product(" ",rs.getString(2)," "," ",rs.getString(5)," "," "," "," ", " ");
				prods.add(prod);
			}
			
	
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return prods;
	}
	public ArrayList<ItemOrdered> viewProductOnSaleBarChart() {
		ArrayList<ItemOrdered> iord = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "select productName, (count(productName)*price) as totalSales  from customerorder " +
							"group by productName ";
			rs = stmt.executeQuery(view);
			
			
			String prNames = null;
		
			
			String totalSales = null;
		
					
			iord = new ArrayList<ItemOrdered>();
			ItemOrdered ioItem = null;
			while(rs.next())
			{
				
				 prNames = rs.getString("productName");
				 
				 totalSales = rs.getString("totalSales");
				 ioItem = new ItemOrdered(" ", prNames, " ",totalSales, " ");
				 iord.add(ioItem);
			}
			
			 
			 
			rs.close();
			stmt.close();
			conn.close();
			return iord;
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return iord;
	}

	public void deleteOrderDetails(String productName, String username) {
		try 
		{			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb", "root", "tiger");
			stmt = conn.createStatement();
			
			String deleteQuery;
			
			if(productName == null)
			{
				deleteQuery = "DELETE from customerorder";	
			}
			else
			{
				deleteQuery = "DELETE from customerorder where creditCardNo = '"+productName +"'  AND userName = '"+username+"';";
				System.out.println(deleteQuery);
			}
					
			stmt.executeUpdate(deleteQuery);
						
			stmt.close();
			conn.close();
		} catch (SQLException se) { // Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	
		
	}
	public void deleteProduct(String productName) {
		try 
		{			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb", "root", "tiger");
			stmt = conn.createStatement();
			
			String deleteQuery;
			
			if(productName == null)
			{
				deleteQuery = "DELETE from products";	
			}
			else
			{
				deleteQuery = "DELETE from products where prodName = '"+productName+"';";
			}
					
			stmt.executeUpdate(deleteQuery);
						
			stmt.close();
			conn.close();
		} catch (SQLException se) { // Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	public Product selectedProductUpdate(String productName) {
		
		Product prod = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
			stmt = conn.createStatement();			
			String view = "SELECT * FROM products where prodName = '" + productName + "'";
			rs = stmt.executeQuery(view);
			
			while(rs.next()){
				 prod = new Product(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			
			}
			
	
		}
		catch(SQLException se)
		{	//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{	//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
	            stmt.close();
			}
			catch(SQLException se2)
			{
				
			}
			
			try
			{
				if(conn!=null)
	            conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return prod;
	}
}
