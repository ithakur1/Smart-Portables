

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderPlacement {

	HttpServletRequest request;
	private String pHtml = null;
	//private PrintWriter pw = null;
	PrintWriter pw;
	Customer c = new Customer();
	HttpSession session;
//	HttpSession session = request.getSession(true);
	String uname = "";
	
	
	
	public OrderPlacement(HttpServletRequest request, PrintWriter pw) {
		this.request = request;
		this.pw = pw;
		this.session = request.getSession(true);
		this.uname = session.getAttribute("userid") == null ? "" : session.getAttribute("userid").toString(); // if user not logged in then uname = ""
//		System.pw.println("Indranil");
	}
	
	public void storeProduct(String id,String name,String price, String retailer){
		
		if(!uname.isEmpty() && !BuyHashMap.orders.containsKey(uname)){	
			ArrayList<ItemOrdered> arr = new ArrayList<ItemOrdered>();
			BuyHashMap.orders.put(uname, arr);
		}
		//gets the current login session
		
		ArrayList<ItemOrdered> ItemsOrdered = BuyHashMap.orders.get(uname);
		
		SAXParserDataStore sx = new SAXParserDataStore();
		ProductCatalog pc = sx.prodCatalog.get(id);
		
		Random rn = new Random();
		Integer randomNum =  rn.nextInt(101) + 1;
		
		//on click  of delete get randomnum matching and delete it from the list of orders
		ItemOrdered io = new ItemOrdered(randomNum.toString(), pc.getName(), pc.getPrice(), pc.getImage(), pc.getRetailer());
		ItemsOrdered.add(io);
	}
	
	public ArrayList<ItemOrdered> getCustomerOrders(){
		ArrayList<ItemOrdered> order = new ArrayList<ItemOrdered>(); 
		if(BuyHashMap.orders.containsKey(uname))
			order= BuyHashMap.orders.get(uname);
		return order;
	}
	
	//everywhere from the canceel button click after selection of items
	public int CancelOrder(){
		BuyHashMap.orders.clear();
		
		return 0;
	}
	
	public void deleteProduct(String id) {
		ArrayList<ItemOrdered> orders = null;
		if(BuyHashMap.orders != null && !BuyHashMap.orders.isEmpty() && BuyHashMap.orders.size()>0)
		{
			orders = BuyHashMap.orders.get(this.uname);
			for(int i=0; i<orders.size(); i++)
			{
				if(orders.get(i).getItemId().equals(id))
					orders.remove(i);
			}
		}
	}

	
	public int CartCount(){
		if(!uname.isEmpty() && BuyHashMap.orders.containsKey(uname))
		return getCustomerOrders().size();
		return 0;
	}

	public void logout() {
		session.removeAttribute(uname);
		
	}
	
	

	public void writeReviewContent(ProductCatalog pc,String category) {
		System.out.println(pc.getName());
		String docType = "<!doctype html>\n";
		pw.println(docType+"<html>"+"<head>"+"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
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
		       // "<li><a style='font-size: 20px;' href='Buy'>Cart("+helper.CartCount()+")</a></li>"+

		        
		      "</ul>");
				
								
			
				
pw.println(" </ul>"+
		"</nav>"+

		"<div id='body'>"+
			"<section id='content'>"+
				"<article>"+
					"<h2 align='center'>Write Review</h2>"+
				"</article>"+
				"<article class='expanded'>"+					
					"<table>"
					);
		/*StringBuilder fetchHtml = new StringBuilder(fetchHtmlContent("C:/Users/ithak/workspace_ewa/Copy_of_Demo2/WebContent/writereview.html"));
		StringBuilder dynamicHtml = new StringBuilder();
		
		/*<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
				+ "<title>GadgetStation</title></head><section id='content'><article><h2 align='center'>Write Review</h2></article>"
				+ "<article class='expanded'><table>*/
		
		pw.println("<form method = 'post' action = 'WriteReviewServlet'>");				
		
		pw.println("<tr>");				
		pw.println("<td>");
		pw.println("Product Model Name: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='productmodelname' value='"+pc.getName()+"' readonly>");
		pw.println("</td>");
		pw.println("</tr>");
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Category: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='category' value='"+category+"' readonly");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Product Price: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='productprice' value='"+pc.getPrice()+"' readonly>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Retailer Name: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='retailername' value='"+pc.getRetailer()+"' readonly>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Retailer Zip: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='retailerzip'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Retailer City: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='retailercity'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Retailer State: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='retailerstate'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Product on Sale: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='productonsale'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Manufacturer Name: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='manufacturername'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Manufacturer Rebate: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='manufacturerrebate'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("UserID: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='userid'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("UserAge: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='userage'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("UserGender: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='usergender'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("UserOccupation: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='useroccupation'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");				
		pw.println("<td>");
		pw.println("Review Rating: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='reviewrating'>");
		pw.println("</td>");
		
		pw.println("</tr>");	
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Review Date: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='reviewdate'>");
		pw.println("</td>");
		pw.println("</tr>");	
		
		pw.println("<tr>");	
		pw.println("<td>");
		pw.println("Review Text: ");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<input type='text' name='reviewtext'>");
		pw.println("</td>");
		pw.println("</tr>");										
	
		pw.println("<tr>");			
		pw.println("<td align='center' colspan='2'>");			
		pw.println("<input type = 'submit' name = 'Submit Review' value = 'Submit Review'>");			
		pw.println("</td>");
		pw.println("</tr>");
		pw.println("</form>");	
		/*pHtml = modifyHtml(fetchHtml.toString(),dynamicHtml.toString(),"#WriteReview");				
		pw.append(pHtml);*/
		pw.println("</table>"+
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
