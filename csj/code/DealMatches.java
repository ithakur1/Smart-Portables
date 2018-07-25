
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DealMatches extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		PrintWriter out = response.getWriter();
		MySqlDataStoreUtilities dataStoreUtilities =new MySqlDataStoreUtilities();
		
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
	        "<li><a style='font-size: 20px;'href='Buy'>Cart()</a></li>"+
	        "<li><a href='Logout'>Logout</a></li>"+

	        
	      "</ul>");
		out.println(" </ul>"+
				"</nav>"+

				"<div id='body'>"+
					"<section id='content'>"+
						
						"<article class='expanded'>"				
							);
			
		
		HashMap<String, Product> selectedProducts = new HashMap<String, Product>();
		
		try
		{
	        out.println("<table>");
	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>");
			out.println("<a href='#'>Welcome to Best Deal</a></h2>");
	        out.println("</td>");
	        out.println("</tr>");

	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>The World trust us for The best deals website</h2>");
	        out.println("</td>");
	        out.println("</tr>");

	        out.println("<tr>");
	        out.println("<td>");
			out.println("<h2>We beat our competitor in all aspects. Price-Match Guaranteed</h2>");
	        out.println("</td>");
	        out.println("</tr>");

			String line= null;
			ArrayList<Product> productMap = new ArrayList<Product>();
			productMap = dataStoreUtilities.viewProductsDetails();
			
			for(Product bean : productMap)
			{
					if((selectedProducts.size() < 2 ) && !selectedProducts.containsKey(bean.getProdName()))
					{
						BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:/apache-tomcat-7.0.34/webapps/csj/DealMatches.txt")));
						line = bufferedReader.readLine();
						if(line == null)
						{
					        out.println("<tr>");
					        out.println("<td>");

							out.println("<h2 align='center'>NO OFFERS FOUND</h2>");
							out.println("</td>");
					        out.println("</tr>");
							break;	
						}
						else
						{
//							line = bufferedReader.readLine();
							do
							{
								if(line.contains(bean.getProdName()))
								{
									out.println("<tr>");
							        out.println("<td>");
								    out.print("<h3 style='color:blue;'>"+line+"</h3>");								
									selectedProducts.put(bean.getProdID(), bean);
									out.println("</td>");
							        out.println("</tr>");						        
								}
								line = bufferedReader.readLine();
							}while(line != null);
						}
						bufferedReader.close();
					  }					
//				    }
			      }
			
			

			
			 out.println("</table>");
				Set set = selectedProducts.entrySet();
				Iterator iterator = set.iterator();
				
			out.println("<table>");	
			out.println("<tr>");	
				while(iterator.hasNext())
				{
					Map.Entry<String, Product> map = (Map.Entry)iterator.next();
					Product bean = (Product)map.getValue();
					
					out.println("<td>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<td >");
			        out.println("<h2>Product Id<h2>");
			        out.println("</td>");
			        
			        out.println("<td>");
			        out.println("<h2>" + bean.getProdID() + "</h2>");
			        out.println("</td>");	
			        out.println("</tr>");
			        
			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Retailer   	:");
			        out.println("</td>");
			        out.println("<td>");
			    	out.println("<a>"  + bean.getRetailer() + "</a>");
			        out.println("</td>");	
			        out.println("</tr>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Product Name   :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + bean.getProdName() + "</a>");
			        out.println("</td>");	
			        out.println("</tr>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Condition      :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + bean.getCondition() + "</a>");
			        out.println("</td>");
			        out.println("<td>");

			        out.println("<tr>");
			        out.println("<td>");
			        out.println("Price 		    :");
			        out.println("</td>");
			        out.println("<td>");
			        out.println("<a>"  + bean.getProdType() + "</a>");
			        out.println("</td>");
			        out.println("<td>");
			        
			      //  out.println("<input type ='hidden' name='id' value="+bean.getProdID()+">");
			        String link="<form method = 'post' action = 'Buy'><input type='hidden' name='id' value='"+bean.getProdID()+"'><input type='hidden' name='name' value='"+bean.getProdName()+"'><input type='hidden' name='price' value='"+bean.getProdPrice()+"'><input type='hidden' name='retailer' value='"+bean.getRetailer()+"'><input type = 'submit' name = 'Buy' value = 'Add to Cart'></form>";
			        String WriteReview ="<form method = 'get' action = 'WriteReviewServlet'><input type='hidden' name='id' value='"+bean.getProdID()+"'><input type = 'submit' name = 'writeReview' value = 'Write Review'></form>";
			        String ReadReview   ="<form method = 'get' action = 'DisplayReviewServlet'><input type='hidden' name='id' value='"+bean.getProdName()+"'><input type = 'submit' name = 'readReview' value = 'Read Review'></form>";
			        out.println("<tr>");
			        out.println("<td>");
			        out.println(link);
			        out.println("</td>");
			        out.println("<td text-align='left'>");
			  	    out.println(WriteReview);
			  	    out.println("</td>");
			  	    out.println("<td text-align='left'>");
			  	    out.println(ReadReview);
			  	    out.println("</td>");
			  	    out.println("</tr>");
			  	    out.println("</table>");
			  	    out.println("</td>");

//			        out.println(
//					"<form method = 'post' action = 'Buy'>"+
//					"<input type='hidden' name='id' value='"+bean.getProdID()+"'>"+
//					"<input type='hidden' name='name' value='"+bean.getProdName()+"'>"+
//					"<input type='hidden' name='price' value='"+bean.getProdPrice()+"'>"+
//					"<input type='hidden' name='retailer' value='"+bean.getRetailer()+"'>"+
//						"<input type = 'submit' name = 'Buy' value = 'Add to Cart'>"+
//					"</form>"+
//					"<form method = 'get' action = 'WriteReviewServlet'>"+
//					"<input type='hidden' name='id' value='"+bean.getProdID()+"'>"+
//						"<input type = 'submit' name = 'writeReview' value = 'Write Review'>"+
//					"</form>"+	
//					"<form method = 'get' action = 'DisplayReviewServlet'>"+
//					"<input type='hidden' name='id' value='"+bean.getProdName()+"'>"+
//						"<input type = 'submit' name = 'readReview' value = 'Read Review'>"+
//					"</form>"+	
//				"</td>");
			  	    

					
				}	
				out.println("</tr>");
				out.println("</table></article></section>");
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	

		out.println("<aside class='sidebar'>"+
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
				"<li><a href='ViewCustOrders'>My Orders</a></li>"+
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
