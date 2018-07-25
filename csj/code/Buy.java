

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
	public static HashMap<String, ProductCatalog> hm = new HashMap<String, ProductCatalog>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		OrderPlacement helper = new OrderPlacement(request, out);
		String id = request.getParameter("id");
		helper.deleteProduct(id);
		displayCart(request, response);
		helper.CancelOrder();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		OrderPlacement helper = new OrderPlacement(request, out);
		HttpSession session = request.getSession(true);
		String uname = session.getAttribute("userid") == null ? "" : session.getAttribute("userid").toString();
		if (uname.isEmpty() && (uname.equals("") || uname.length() == 0)) {// making
																			// sure
																			// that
																			// the
																			// user
																			// session
																			// is
																			// available
			session.setAttribute("login_message", "Please Login to add items to cart");
			response.sendRedirect("login.html");
			return;
		}
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String retailer = request.getParameter("retailer");

		helper.storeProduct(id, name, price, retailer);
		displayCart(request, response);
	}

	protected void displayCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		OrderPlacement helper = new OrderPlacement(request, out);
		HttpSession sess = request.getSession(true);
		String uname = "";
		uname = sess.getAttribute("userid") == null ? "" : sess.getAttribute("userid").toString();
		if (uname.isEmpty() && (uname.equals("") || uname.length() == 0)) {// making
																			// sure
																			// that
																			// the
																			// user
																			// session
																			// is
																			// available
			// HttpSession session = request.getSession(true);
			sess.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}

		SAXParserDataStore saxHandler = new SAXParserDataStore();
		try {
			hm = saxHandler.readDataFromXML("C:/apache-tomcat-7.0.34/webapps/csj/WEB-INF/ProductCatalog.xml");
		} catch (ParserConfigurationException e) {
			System.out.println("Error - ParserConfigurationException");
		} catch (org.xml.sax.SAXException e) {

			e.printStackTrace();
		}

		String docType = "<!doctype html>\n";
		out.println(
				docType + "<html>" + "<head>" + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
						+ "<title>GadgetStation</title>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>"
						+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>"
						+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'></script>"
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
						"<li><a style='font-size: 20px;' href='Buy'>Cart(" + helper.CartCount() + ")</a></li>" +

						"</ul>");
		out.println("");
		out.print("<div id='body'><section id='content'><article>");
		out.print("<a style='font-size: 24px;'>Cart(" + helper.CartCount() + ")</a>");
		out.print("</h2><div class='entry'>");
		if (helper.CartCount() > 0) {
			out.print("<table  class='gridtable'>");
			int i = 1;
			int total = 0;
			for (ItemOrdered oi : helper.getCustomerOrders()) {
				out.print("<form method='get' action='Buy'>");
				out.print("<tr>");
				out.print("<td>" + i + ".</td><td>" + oi.getName() + "</td><td>: " + oi.getPrice() + "</td>" +

						"<input type='hidden' name='id' value='" + oi.getItemId() + "'>" +

						"<td><input type='submit' class='button' value='Delete Item' href='#'></td>"
						+ "</input></form>");
				out.print("</tr>");
				total = total + Integer.parseInt(oi.getPrice());
				i++;
			}
			out.print("<tr><th></th><th>Total in $</th><th>" + total + "</th>");
			out.print("<form method='get' action='CheckOut'>" + "<input type='hidden' name='totalamount' value='"
					+ total + "'>" + "<input type='hidden' name='type' value=''>" +

					"<input type='hidden' name='access' value=''>"
					+ "<input type='submit' class='button' value='Check Out' href='#'>" + "</input></form>"
					+ "<form method='get' action='Buy'>" +

					"<input type='submit' class='button' value='Cancel Order' href='#'>" + "</input></form>"
					+ "<a href='CustomerServlet'>Continue Shopping</a>"

			);
			// out.print("<tr><td></td><td></td><td><a href='CheckOut'
			// class='btnbuy'>Check Out</a></td>");
			out.print("<tr><td></td><td></td>");
			out.print("</table>");
		} else {
			out.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		
		out.println("<div class='container'>"+
				  "<h3>Accessories</h3>"+  
				  "<div id='myCarousel' class='carousel slide' data-ride='carousel'>"+
				   

					"<ol class='carousel-indicators'>"+
				      "<li data-target='#myCarousel' data-slide-to='0' class='active'></li>"+
				      "<li data-target='#myCarousel' data-slide-to='1'></li>"+
				      "<li data-target='#myCarousel' data-slide-to='2'></li>"+
				    "</ol>"+

				    
				    "<div class='carousel-inner'>"+
				      "<div class='item active'>"+
				        "<img src='images/mouse.jpg' alt='Mouse Pad' style='width:20%;'>"+
						        "<div class='carousel-caption'>"+
				          "<a href='AccessoryServlet'>"+
						  "<button type='button' class='btn btn-default'>Details</button></a>"+
				        "</div>"+
				      "</div>"+

				      "<div class='item'>"+
				        "<img src='images/phone_case.jpg' alt='Headphone' style='width:20%;'>"+
						"<div class='carousel-caption'>"+
				          "<a href='AccessoryServlet'>"+
						  "<button type='button' class='btn btn-default'>Details</button></a>"+
				        "</div>"+
				      "</div>"+
				    
				      "<div class='item'>"+
				        "<img src='images/charger.jpg' alt='ChargeCable' style='width:20%;'>"+
						"<div class='carousel-caption'>"+
				          "<a href='AccessoryServlet'>"+
						  "<button type='button' class='btn btn-default'>Details</button></a>"+
				        "</div>"+
				      "</div>"+
				    "</div>"+

				    "<a class='left carousel-control' href='#myCarousel' data-slide='prev'>"+
				      "<span class='glyphicon glyphicon-chevron-left'></span>"+
				      "<span class='sr-only'>Previous</span>"+
				    "</a>"+
				    "<a class='right carousel-control' href='#myCarousel' data-slide='next'>"+
				      "<span class='glyphicon glyphicon-chevron-right'></span>"+
				      "<span class='sr-only'>Next</span>"+
				    "</a>"+
				  "</div>"+
				"</div>");
//		out.println("<div class='container'>"
//				+ "<div id='carousel-example-generic' class='carousel slide' data-ride='carousel'>" +
//
//				"<ol class='carousel-indicators'>"
//				+ "<li data-target='#carousel-example-generic' data-slide-to='0' class='active'></li>"
//				+ "<li data-target='#carousel-example-generic' data-slide-to='1'></li>"
//				+ "<li data-target='#carousel-example-generic' data-slide-to='2'></li>" +
//
//				"</ol>");
//		for (ProductCatalog pc : hm.values()) {
//			// checks for null
//			if (pc.getId().startsWith("ac")) {
//
//				out.println("<div class='carousel-inner' role='listbox'>" + " <div class='item active'>" + "<img src='"
//						+ pc.getImage() + "' width = '100' height = '100' alt='...'>" + "<div class='carousel-caption'>"
//						+ "<form method = 'post' action = 'Buy'>" + "<input type='hidden' name='id' value='"
//						+ pc.getId() + "'>" + "<input type='hidden' name='name' value='" + pc.getName() + "'>"
//						+ "<input type='hidden' name='price' value='" + pc.getPrice() + "'>"
//						+ "<input type='hidden' name='retailer' value='" + pc.getRetailer() + "'>"
//						+ "<input type = 'submit' name = 'Buy' value = 'Add to Cart'>" + "</form>" + "</div>" + "</div>"
//				/*
//				 * "<div class='item'>"+
//				 * "<img src='images/Phones/phone8.png' width = '250' height = '250' alt='...'>"
//				 * + "</div>"+ "<div class = 'item'>"+
//				 * "<img src = 'images/Phones/phone10.jpg' width = '250' height = '250' alt = '..'>"
//				 * + "</div>"
//				 */);
//
//			}
//		}
//
//		out.println("</div>" +
//
//				"<a class='left carousel-control' href='#carousel-example-generic' role='button' data-slide='prev'>"
//				+ "<span class='glyphicon glyphicon-chevron-left' aria-hidden='true'></span>"
//				+ "<span class='sr-only'>Previous</span>" + "</a>"
//				+ "<a class='right carousel-control' href='#carousel-example-generic' role='button' data-slide='next'>"
//				+ "<span class='glyphicon glyphicon-chevron-right' aria-hidden='true'></span>"
//				+ "<span class='sr-only'>Next</span>" + "</a>" + "</div></div>");

		out.print("</article></section></div>");
		out.println("<aside class='sidebar'>" + "<ul>" + "<li>" + "<h4>Navigation</h4>" + "<ul>"
				+ "<li><a href='index.html'>Home Page</a></li>" + "<li><a href='WatchServlet'>Smart Watches</a></li>"
				+ "<li><a href='SpeakerServlet'>Speakers</a></li>"
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

	}

}
