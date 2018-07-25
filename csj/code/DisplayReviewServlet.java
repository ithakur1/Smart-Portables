import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

@WebServlet("/DisplayReviewServlet")
public class DisplayReviewServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		OrderPlacement helper = new OrderPlacement(request, out);
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
						"<li><a style='font-size: 20px;' href='Buy'>Cart(" + helper.CartCount() + ")</a></li>" +

						"</ul>");

		out.println(" </ul>" + "</nav>" +

				"<div id='body'>" + "<section id='content'>" + "<article>" + "<h2 align='center'>User Reviews</h2>"
				+ "</article>" + "<article class='expanded'>" + "<table>" + "<tr>" + "<th colspan='3'>" +
				// "<h2 align='center'>List of phones</h2>"+
				"</th>" + "</tr>");
		

		try {
			// Get the values from the form
			String prodID = request.getParameter("id");

			// Connect to Mongo DB
			MongoClient mongo = new MongoClient("localhost", 27017);

			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CustomerReviews");

			DBCollection myReviews = db.getCollection("myNewReviews");

			// Find and display
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("productmodelname", prodID);

			DBCursor cursor = myReviews.find(searchQuery);

			out.println("<table>");
			while (cursor.hasNext()) {

				BasicDBObject obj = (BasicDBObject) cursor.next();

				out.println("<tr>");
				out.println("<td> Product Name: </td>");
				out.println("<td>" + obj.getString("productmodelname") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Category</td>");
				out.println("<td>" + obj.getString("category") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Product Price :</td>");
				out.println("<td>" + obj.getString("productprice") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Retailer Name :</td>");
				out.println("<td>" + obj.getString("retailername") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Retailer Zip :</td>");
				out.println("<td>" + obj.getString("retailerzip") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Retailer City :</td>");
				out.println("<td>" + obj.getString("retailercity") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Retailer State :</td>");
				out.println("<td>" + obj.getString("retailerstate") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Product on Sale:</td>");
				out.println("<td>" + obj.getString("productonsale") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> User ID: </td>");
				out.println("<td>" + obj.getString("userid") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Manufacturer Rebate: </td>");
				out.println("<td>" + obj.getString("manufacturerrebate") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> User Occupation: </td>");
				out.println("<td>" + obj.getString("useroccupation") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Review Rating: </td>");
				out.println("<td>" + obj.getString("reviewrating").toString() + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> User Age: </td>");
				out.println("<td>" + obj.getString("userage") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Manufacturer Name: </td>");
				out.println("<td>" + obj.getString("manufacturername") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> User Review : </td>");
				out.println("<td>" + obj.getString("reviewtext") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> User Gender : </td>");
				out.println("<td>" + obj.getString("usergender") + "</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td> Review Date: </td>");
				out.println("<td>" + obj.getString("reviewdate") + "</td>");
				out.println("</tr>");

				out.println("<tr><td></td><td></td></tr>");

			}
			out.println("</table>");
		} catch (MongoException e) {
			e.printStackTrace();
		}
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
		out.print("</div></div></div>");

	}
}