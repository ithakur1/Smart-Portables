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
import com.mongodb.client.AggregateIterable;
import com.mongodb.AggregationOutput;
import com.mongodb.client.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

@WebServlet("/Trending")
public class Trending extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reqType = request.getParameter("req");

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

				"<div id='body'>" + "<section id='content'>" + "<article>" +
				// "<h2 align='center'>Welcome to Phones Station</h2>"+
				"</article>" + "<article class='expanded'>" + "<table>" + "<tr>" + "<th colspan='3'>" +
				// "<h2 align='center'>List of phones</h2>"+
				"</th>" + "</tr>");
		out.print("<div id='content'><div class='post'><h2 class='title meta'>");
		out.print("</h2><div class='entry'>");

		try {
			// Connect to Mongo DB
			MongoClient mongo = new MongoClient("localhost", 27017);

			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CustomerReviews");

			DBCollection myReviews = db.getCollection("myNewReviews");

			if (reqType.equals("ratings")) {
				BasicDBObject gtQuery = new BasicDBObject();
				gtQuery.put("reviewrating", new BasicDBObject("$gt", "3"));
				DBCursor cursor = myReviews.find(gtQuery);
				out.print("<table>");
				while (cursor.hasNext() != false) {

					BasicDBObject obj = (BasicDBObject) cursor.next();
					// out.print("<table>");
					out.print("<tr>");
					out.print("<td> Product Name: </td>");
					out.print("<td>" + obj.getString("productmodelname") + "</td>");
					out.print("</tr>");

					out.print("<tr>");
					out.print("<td> Review Rating: </td>");
					out.print("<td>" + obj.getString("reviewrating").toString() + "</td>");
					out.print("</tr>");

					out.print("<tr>");
					out.print("<td> Review Date: </td>");
					out.print("<td>" + obj.getString("reviewdate") + "</td>");
					out.print("</tr>");

					out.print("<tr>");
					out.print("<td> User Review : </td>");
					out.print("<td>" + obj.getString("reviewtext") + "</td>");
					out.print("</tr>");

					out.print("<tr><td></td><td></td></tr>");

				}
				out.print("</table>");
				out.println("</table>" + "</article>" + "</section>" + "<aside class='sidebar'>" + "<ul>" + "<li>"
						+ "<h4>Navigation</h4>" + "<ul>" + "<li><a href='CustomerServlet'>Home Page</a></li>"
						+ "<li><a href='WatchServlet'>Smart Watches</a></li>"
						+ "<li><a href='SpeakerServlet'>Speakers</a></li>"
						+ "<li><a href='HeadphoneServlet'>Headphones</a></li>"
						+ "<li><a href='LaptopServlet'>Laptops</a></li>" +

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
			} else if (reqType.equals("zips")) {
				DBObject groupFields = new BasicDBObject("_id", "$retailerzip");

				groupFields.put("retailerzip", new BasicDBObject("$sum", 1));
				DBObject group = new BasicDBObject("$group", groupFields);
				DBObject sortFields = new BasicDBObject("retailerzip", -1);
				DBObject sort = new BasicDBObject("$sort", sortFields);
				AggregationOutput output = myReviews.aggregate(group, sort);

				out.print("<table>");
				out.print("<tr><td> ZIP Code: </td><td> Number of products sold: </td></tr>");
				int i = 1;
				for (DBObject obj : output.results()) {

					String id = obj.get("_id").toString();
					String times = obj.get("retailerzip").toString();

					out.print("<tr><td>" + obj.get("_id").toString() + "</td><td>" + obj.get("retailerzip").toString()
							+ "</td></tr>");
					i++;
					if (i == 6)
						break;

				}
				out.print("</table>");
				out.println("</table>" + "</article>" + "</section>" + "<aside class='sidebar'>" + "<ul>" + "<li>"
						+ "<h4>Navigation</h4>" + "<ul>" + "<li><a href='CustomerServlet'>Home Page</a></li>"
						+ "<li><a href='WatchServlet'>Smart Watches</a></li>"
						+ "<li><a href='SpeakerServlet'>Speakers</a></li>"
						+ "<li><a href='HeadphoneServlet'>Headphones</a></li>"
						+ "<li><a href='LaptopServlet'>Laptops</a></li>" +

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
			} else {// sales
				DBObject groupFields = new BasicDBObject("_id", "$productmodelname");

				groupFields.put("productmodelname", new BasicDBObject("$sum", 1));
				DBObject group = new BasicDBObject("$group", groupFields);
				DBObject sortFields = new BasicDBObject("productmodelname", -1);
				DBObject sort = new BasicDBObject("$sort", sortFields);
				AggregationOutput output = myReviews.aggregate(group, sort);

				out.print("<table>");
				out.print("<tr><td> Product name: </td><td> Number of pieces sold: </td></tr>");
				int i = 1;
				for (DBObject obj : output.results()) {
					// out.print(obj);
					String id = obj.get("_id").toString();
					String times = obj.get("productmodelname").toString();

					out.print("<tr><td>" + obj.get("_id").toString() + "</td><td>"
							+ obj.get("productmodelname").toString() + "</td></tr>");
					i++;
					if (i == 6)
						break;
				}
				out.print("</table>");
				out.println("</table>" + "</article>" + "</section>" + "<aside class='sidebar'>" + "<ul>" + "<li>"
						+ "<h4>Navigation</h4>" + "<ul>" + "<li><a href='CustomerServlet'>Home Page</a></li>"
						+ "<li><a href='WatchServlet'>Smart Watches</a></li>"
						+ "<li><a href='SpeakerServlet'>Speakers</a></li>"
						+ "<li><a href='HeadphoneServlet'>Headphones</a></li>"
						+ "<li><a href='LaptopServlet'>Laptops</a></li>" +

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
			out.print("</div></div></div>");
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}