

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




@WebServlet("/AccessoryServlet")
public class AccessoryServlet extends HttpServlet {
	
	public static HashMap<String, ProductCatalog> hm = new HashMap<String, ProductCatalog>();
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException	{		
			
		PrintWriter out = response.getWriter();		

		
		OrderPlacement helper = new OrderPlacement(request,out);
		HttpSession sess = request.getSession(true);
		
		SAXParserDataStore saxHandler = new SAXParserDataStore();
		try
		{
			hm = saxHandler.readDataFromXML("C:/Users/ithak/workspace_ewa/Assignment_2/WebContent/WEB-INF/ProductCatalog.xml");
		}
		catch(ParserConfigurationException e)
		{
			System.out.println("Error - ParserConfigurationException");
		} catch (org.xml.sax.SAXException e) {
			
			e.printStackTrace();
		}
			
		String docType = "<!doctype html>\n";
			out.println(docType+"<html>"+"<head>"+"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
				"<title>GadgetStation</title>"+
				"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"+
			"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>"+
				"<link rel='stylesheet' href='styles.css' type='text/css' />"+
				"</head>"+"<body>"+
		"<div id='container'>"+
			"<header>"+
				
				"<h1><a href='/'>Gadget<span>Station </span></a></h1>"+				
				"<h2 class='caption'>Save money, save time.</h2>"+
			"</header>"+
			"<div class='navbar-header'>"+
			      "<button type='button' class='navbar-toggle collapsed' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1' aria-expanded='false'>"+
			        "<span class='sr-only'>Toggle navigation</span>"+
			        "<span class='icon-bar'></span>"+
			        "<span class='icon-bar'></span>"+
			        "<span class='icon-bar'></span>"+
			      "</button>"+
			      "<a class='navbar-brand' href='CustomerServlet'><span class='glyphicon glyphicon-picture' aria-hidden='true'></span>HOME</a>"+
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
			       // "<li><a href='signUp.html'>Sign Up<i class='fa fa-user-plus' aria-hidden='true'></i></a></li>"+
			        "<li><a style='font-size: 20px;' href='Buy'>Cart("+helper.CartCount()+")</a></li>"+

			        
			      "</ul>");
					
									
				
					
	out.println(" </ul>"+
			"</nav>"+

			"<div id='body'>"+
				"<section id='content'>"+
					"<article>"+
						"<h2 align='center'>Welcome</h2>"+
					"</article>"+
					"<article class='expanded'>"+					
						"<table>"+
						"<tr>"+
						"<th colspan='3'>"+
						"<h2 align='center'>List of Accessories</h2>"+
						"</th>"+
						"</tr>");
		
		for (ProductCatalog pc : hm.values()) 
		{
			// checks for null
			if (pc.getId().startsWith("ac")) 
			{
				
				out.println("<tr>"+
				
				"<td>"+
					"<img src = '"+ pc.getImage() +"' width = '250' height = '250' alt = 'phone'>"+
				"</td>"+
				"<td>"+
					"<p>Model: "+ pc.getName() +"</p>"+
					"<p>Sold by: "+ pc.getRetailer()+ "</p>"+
					"<p> Condition: "+ pc.getCondition()+ "</p>"+
					"<p> Price: "+ pc.getPrice()+ "</p>"+
				"</td>"+
				"<td align='center'>"+
					"<form method = 'post' action = 'Buy'>"+
					"<input type='hidden' name='id' value='"+pc.getId()+"'>"+
					"<input type='hidden' name='name' value='"+pc.getName()+"'>"+
					"<input type='hidden' name='price' value='"+pc.getPrice()+"'>"+
					"<input type='hidden' name='retailer' value='"+pc.getRetailer()+"'>"+
						"<input type = 'submit' name = 'Buy' value = 'Add to Cart'>"+
					"</form>"+
					"<form method = 'get' action = 'WriteReviewServlet'>"+
					"<input type='hidden' name='id' value='"+pc.getId()+"'>"+
						"<input type = 'submit' name = 'writeReview' value = 'Write Review'>"+
					"</form>"+	
					"<form method = 'get' action = 'DisplayReviewServlet'>"+
					"<input type='hidden' name='id' value='"+pc.getName()+"'>"+
						"<input type = 'submit' name = 'readReview' value = 'Read Review'>"+
					"</form>"+	
				"</td>"+
			"</tr>");			
			}		
		}
			
			out.println("</table>"+
					"</article>"+
				"</section>"+
				"<aside class='sidebar'>"+
					 "<ul>"+
						"<li>"+
							"<h4>Navigation</h4>"+
							"<ul>"+
								"<li><a href='index.html'>Home Page</a></li>"+
							"<li><a href='WatchServlet'>Smart Watches</a></li>"+
								"<li><a href='SpeakerServlet'>Speakers</a></li>"+
								"<li><a href='HeadphoneServlet'>Headphones</a></li>"+
								"<li><a href='LaptopServlet'>Laptops</a></li>"+
								
								"<li><a href='ESServlet'>External Storage</a></li>"+	
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
