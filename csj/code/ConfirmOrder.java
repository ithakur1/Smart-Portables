
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isUpdated = false;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String username = "";
		HttpSession session = request.getSession(true); 
		if (session.getAttribute("username")!=null)
			username = session.getAttribute("username").toString();
		
		String productlist = "";
		String orderprice = request.getParameter("orderprice");
		String useraddress = request.getParameter("address");
		String creditcardno = request.getParameter("CardNumber");
		String orderusername = username;
		
	
		
		
		if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").toString().equals("salesman")){
			if(session.getAttribute("custSelected")!=null)
				orderusername = session.getAttribute("custSelected").toString();
		}
		int i = 0;
		
		MySqlDataStoreUtilities ms = new MySqlDataStoreUtilities();
		ms.insertOrders(creditcardno);
		
		
		//clearing the orders
		BuyHashMap.orders.clear();
		
		
		

		OrderPlacement helper = new OrderPlacement(request,pw);
		for (ItemOrdered oi : helper.getCustomerOrders()) {
			productlist += oi.getName() + ',';
		}
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		
		pw.print("</h2><div><table id='bestseller'>");
		
		if(session.getAttribute("custSelected")!=null)
			pw.print("<h3>Thank you "+username+" for placing your order for "+ session.getAttribute("custSelected")+" with us. You can now relax.</h3>");				
		else
			pw.print("<h3>Thank you "+username+" Your order has been placed</h3>");				
			
			pw.print("<tr>");
			
				
			pw.print("<ul><li><form method='get' action='CustomerServlet'>" +
					
					"<input type='hidden' name='type' value='consoles'>"+
					
					"<td><div id='shop_item'>" +
						
					"<tr><td><input type='submit' class='button' value='HomePage' href='CustomerServlet'></td></tr>" +
					"<input type='hidden' name='access' value=''></input></form></li>");
					
			pw.print("</ul></div></td>");
			
			pw.print("</tr>");
			
		pw.print("</table></div></div></div>");	
	
	}
}
