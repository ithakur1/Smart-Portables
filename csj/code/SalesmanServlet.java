

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SalesmanServlet")
public class SalesmanServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException	{	
		
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();

		ServletContext sc=request.getSession().getServletContext();
		String username = request.getSession().getAttribute("userid").toString();	
		
String docType = "<!doctype html>\n";
	out.println(docType+"<html>"+"<head>"+"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
		"<title>GadgetStation</title>"+
		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"+
		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>"+
		"<link rel='stylesheet' href='styles.css' type='text/css' />"+
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
    "<a class='navbar-brand' href='SalesmanServlet'><span class='glyphicon glyphicon-picture' aria-hidden='true'></span>HOME</a>"+
  "</div>"+

  //<!-- Collect the nav links, forms, and other content for toggling -->
  "<div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>"+
    /*"<ul class='nav navbar-nav'>"+
      
       "<li><a href='WatchServlet'>Smart Watches</a></li>"+
       "<li><a href='SpeakerServlet'>Speakers</a></li>"+
       "<li><a href='HeadphoneServlet'>Headphones</a></li>"+
      "<li><a href='PhoneServlet'>Phones</a></li>"+
      "<li><a href='LaptopServlet'>Laptops</a></li>"+
	 "<li><a href='ESServlet'>External Storage</a></li>"+
      

        
    "</ul>"+*/
    
"<ul class='nav navbar-nav navbar-right'>"+
    
      "<li><a href='index.html'>Logout</a></li>"+

      
    "</ul>");		
			
out.println(" </ul>"+
	"</nav>"+

	"<div id='body'>"+
		"<section id='content'>"+
		"<h1>Welcome, "+username+"</h1>"+
		"</section>"+
		"<aside class='sidebar'>"+
			 "<ul>"+
				"<li>"+
					"<h4>Console</h4>"+
					"<ul>"+
						"<li><a href='SalesmanServlet'>Home Page</a></li>"+
						"<li><a href='signUp.html'>Add Customer account</a></li>"+
						"<li><a href='#'>Add Customer Orders</a></li>"+
          "<li><a href='#'>Update Customer Order</a></li>"+ 
					"<li><a href='#'>Delete Customer Order</a></li>"+		
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