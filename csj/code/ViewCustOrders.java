

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewCustOrders")
public class ViewCustOrders extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<ItemOrdered> iords = null;
		ItemOrdered ios = null;
		String username = "";
		// HttpSession session = request.getSession(true);
		username = request.getParameter("uname");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String docType = "<!doctype html>\n";
		out.println(
				docType + "<html>" + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
						+ "<title>GadgetStation</title>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>"
						+ "<link rel='stylesheet' href='styles.css' type='text/css' />" + "</head>" + "<body>"
						+ "<div id='container'>" + "<header>" +

						"<h1><a href='/'>Gadget<span>Station </span></a></h1>"
						+ "<h2 class='caption'>Save money, save time.</h2>" + "</header>"
						+ "<div class='navbar-header'>"
						+ "<button type='button' class='navbar-toggle collapsed' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1' aria-expanded='false'>"
						+ "<span class='sr-only'>Toggle navigation</span>" + "<span class='icon-bar'></span>"
						+ "<span class='icon-bar'></span>" + "<span class='icon-bar'></span>" + "</button>"
						+ "<a class='navbar-brand' href='CustomerServlet'><span class='glyphicon glyphicon-picture' aria-hidden='true'></span>HOME</a>"
						+ "</div>" +

						// <!-- Collect the nav links, forms, and other content
						// for toggling -->
						"<div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>"
						+ "<ul class='nav navbar-nav'>" +

						"<li><a href='WatchServlet'>Smart Watches</a></li>"
						+ "<li><a href='SpeakerServlet'>Speakers</a></li>"
						+ "<li><a href='HeadphoneServlet'>Headphones</a></li>"
						+ "<li><a href='PhoneServlet'>Phones</a></li>" + "<li><a href='LaptopServlet'>Laptops</a></li>"
						+ "<li><a href='ESServlet'>External Storage</a></li>" +

						"</ul>" +

						"<ul class='nav navbar-nav navbar-right'>" +
						// "<li><a href='signUp.html'>Sign Up<i class='fa
						// fa-user-plus' aria-hidden='true'></i></a></li>"+
						// "<li><a style='font-size: 20px;'
						// href='Buy'>Cart("+helper.CartCount()+")</a></li>"+

						"</ul>");

		out.println(" </ul>" + "</nav>");
		out.println("<div id='body'>" + "<section id='content'>" + "<article>" + "<h2 align='center'>All Orders</h2>"
				+ "</article>" + "<article class='expanded'>" + "<table>" + "<tr>" + "<th colspan='3'>" +
				// "<h2 align='center'>List of phones</h2>"+
				"</th>" + "</tr>");
		// out.println("<center><h1>All Orders</h1>");
		MySqlDataStoreUtilities ms = new MySqlDataStoreUtilities();
		iords = ms.fetchOrders(username);

		// display cart
		out.print("<table  class='gridtable'>");
		int i = 1;
		int total = 0;
		for (ItemOrdered oi : iords) {
			out.print("<form method='get' action='Buy'>");
			out.print("<tr>");
			out.print("<td>" + i + ".</td><td>" + oi.getName() + "</td><td>: " + oi.getPrice() + "</td><td>: "
					+ oi.getRetailer() + "</td>"  + "<input type='hidden' name='id' value='" + oi.getItemId()
					+ "'>" +

					"<td><input type='submit' class='button' value='Delete Order' href='#'></td>"+
					"</form>");
			out.print("</tr>");
			total = total + Integer
					.parseInt(oi.getPrice().indexOf(",") != -1 ? oi.getPrice().split(",")[0] : oi.getPrice().trim());
			i++;
		}
		out.print("<tr><th></th><th>Total in $</th><th>" + total + "</th></table>");

		// display cart
		out.println("</table>" + "</article>" + "</section>" + "<aside class='sidebar'>" + "<ul>" + "<li>"
				+ "<h4>Navigation</h4>" + "<ul>" + "<li><a href='CustomerServlet'>Home Page</a></li>"
				+ "<li><a href='WatchServlet'>Smart Watches</a></li>" + "<li><a href='SpeakerServlet'>Speakers</a></li>"
				+ "<li><a href='HeadphoneServlet'>Headphones</a></li>" + "<li><a href='LaptopServlet'>Laptops</a></li>"
				+

				"<li><a href='ESServlet'>External Storage</a></li>" + "</ul>" + "</li>" +

				"<li>" + "<h4>About us</h4>" + "<ul>" + "<li class='text'>"
				+ "<p style='margin: 0;'>Gadget Station is one of the recognized leader in the market of e-commerce website where in people get the cheapest price and best customer service.</p>"
				+ "</li>" + "</ul>" + "</li>" + "</ul>" + " </aside>" + "<div class='clear'></div>" + "</div>" +

				"<footer>" + "<div class='footer-content'>" + "<ul>" + "<li><h2>Contact Us</h2></li>" +

				"</ul>" + "<br>" + "<ul>" + "<li><h4>Gadget Station</h4></li>" + "<li>Chicago, IL 60616</li>"
				+ "<li>Indranil Thakur</li>" + "<li>Email :ithakur1@hawk.iit.edu</li>"
				+ "<li>Phone no: +1 312 383 9148</li>" +

				"</ul>" +

				"<div class='clear'></div>" + "</div>" + "<div class='footer-bottom'>"
				+ "<p>&copy; @Copyright GadgetStation Designed by Indranil Thakur</p>" + "</div>" + " </footer>"
				+ "</div>" + "</body>" + "</html>");
		
		out.close();
	}
}