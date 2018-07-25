

import java.io.*;  
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  
import java.util.*;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException	{	
	
			response.setContentType("text/html");
			PrintWriter out= response.getWriter();
			OrderPlacement helper = new OrderPlacement(request,out);
			HttpSession sess = request.getSession(true);
			ServletContext sc=request.getSession().getServletContext();
			String username = sess.getAttribute("userid").toString();	
			
	String docType = "<!doctype html>\n";
		out.println(docType+"<html>"+"<head>"+"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
			"<title>GadgetStation</title>"+
			"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"+
"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>"+
"<link rel='stylesheet' href='styles.css' type='text/css' /><script type='text/javascript' src='javascript.js'></script>"+
			"</head>"+"<body>"+
	"<div id='container'>"+
		"<header>"+
			
			"<h1><a href='/'>Gadget<span>Station</span></a></h1>"+				
			"<h2>Save money, save time.</h2>"+
		"</header>"+
		"<div class='navbar-header'>"+
	      "<button type='button' class='navbar-toggle collapsed' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1' aria-expanded='false'>"+
	        "<span class='sr-only'>Toggle navigation</span>"+
	        "<span class='icon-bar'></span>"+
	        "<span class='icon-bar'></span>"+
	        "<span class='icon-bar'></span>"+
	      "</button>"+
	      "<a class='navbar-brand' href='index.html'><span class='glyphicon glyphicon-picture' aria-hidden='true'></span>HOME</a>"+
	    "</div>"+

	    //<!-- Collect the nav links, forms, and other content for toggling -->
	    "<div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>"+
	      "<ul class='nav navbar-nav'>"+
	        
	         "<li><a href='WatchServlet'>Smart Watches</a></li>"+
	         "<li><a href='SpeakerServlet'>Speakers</a></li>"+
	         "<li><a href='HeadphoneServlet'>Headphones</a></li>"+
	        "<li><a href='PhoneServlet'>Phones</a></li>"+
	        "<li><a href='LaptopServlet'>Laptops</a></li>"+
		 "<li><a href='ESServlet'>External Storage</a></li>"+
	        

	          
	      "</ul>"+
	      
	"<ul class='nav navbar-nav navbar-right'>"+
	        "<li><a style='font-size: 20px;'href='Buy'>Cart("+helper.CartCount()+")</a></li>"+
	        "<li><a href='Logout'>Logout</a></li>"+

	        
	      "</ul>");
					
    	
				
out.println(" </ul>"+
		"</nav>"+
		"<div class='autocompletion'>"+
"<form name='autofillform' action='AjaxUtility'>"+
	"<table border='0' cellpadding='5'>"+
		"<tbody  align='center'>"+
			"<tr>"+
				"<td><input type='text' id='complete-field' size='50' "
				+ "onkeyup='doCompletion()' placeholder='search'></td>"+
			"</tr>"+
			"<tr>"+
				"<td id='auto-row'>"+
					"<table style='width:45%' id='complete-table' class='popupBox'></table>"+
				"</td>"+
			"</tr>"+
		"</tbody>"+
	"</table>"+
"</form>"+
"</div>"+

		"<div id='body'>"+
			"<section id='content'>"+
			"<h1>Welcome, "+username+"</h1>"+
			"<div class='row'>"+
			
			"<div class='col-lg-6 col-sm-4'>"+
			"<div class='thumbnail'>"+
				"<img src='images/5723427ld.jpg'>"+
			"</div>"+
			"</div>"+
			"<div class='col-lg-6 col-sm-4'>"+
			"<div class='thumbnail'>"+
				"<img src='images/71fea9jTevL._SX425_.jpg'>"+
			"</div></div>"
			+ "<div class='col-lg-6 col-sm-4'>"+
			"<div class='thumbnail'>"
			+ "<img src='images/download.jpg'>"
			+ "</div><"
			+ "/div>"+
			"<div class='col-lg-6 col-sm-4'>"+
			"<div class='thumbnail'>"+
				"<img src='images/u_10141261.jpg'>"+
			"</div></div></div>"+
			"</section>"+
			"<aside class='sidebar'>"+
				 "<ul>"+
					"<li>"+
					"<h4>Navigation</h4>"+
					"<ul>"+
						"<li><a href='index.html'>Home Page</a></li>"+
					"<li><a href='PhoneServlet'>Phones</a></li>"+
						"<li><a href='SpeakerServlet'>Speakers</a></li>"+
						"<li><a href='HeadphoneServlet'>Headphones</a></li>"+
						"<li><a href='LaptopServlet'>Laptops</a></li>"+
						"<li><a href='WatchServlet'>Smart Watches</a></li>"+
						"<li><a href='ESServlet'>External Storage</a></li>"+
						"<li><a href='DealMatches'>Deal Matches</a></li>"+
						"<li><a href='ViewCustOrders?uname="+username+"'>My Orders</a></li>"+
					"</ul>"+
					"<h4>Trending</h4>"+
					"<ul>"+
						"<li><a href='Trending?req=ratings'>Top Product by Reviews</a></li>"+
						"<li><a href='Trending?req=zips'>Top Products by Zipcode</a></li>"+
						"<li><a href='Trending?req=sales'>Top Sold Products</a></li>"+
					"</ul>"+
				"</li>"+
			
"<li>"+
"<h4>About us</h4>"+
"<ul>"+
"<li class='text'>"+
"<p style='margin: 0;'>Gadget Station is one of the recognized leader in the market of e-commerce website where in people get the cheapest price and best customer service.</p>"+
"</li>"+
"</ul>"+
"</li>"+			
"</ul>"+	
" </aside>"+
"<div class='clear'></div>"+
"</div>"+

"<footer>"+
"<div class='footer-content'>"+
"<ul>"+
"<li><h2>Contact Us</h2></li>"+

"</ul>"+
"<br>"+
"<ul>"+
"<li><h4>Gadget Station</h4></li>"+
"<li>Chicago, IL 60616</li>"+
"<li>Indranil Thakur</li>"+
"<li>Email :ithakur1@hawk.iit.edu</li>"+
"<li>Phone no: +1 312 383 9148</li>"+

"</ul>"+



"<div class='clear'></div>"+
"</div>"+
"<div class='footer-bottom'>"+
"<p>&copy; @Copyright GadgetStation Designed by Indranil Thakur</p>"+
"</div>"+
" </footer>"+
"</div>"+
"</body>"+
"</html>");	
}

}
