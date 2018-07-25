

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
		HashMap<String, Product> products = null;   
	   MySqlDataStoreUtilities sqlData = new MySqlDataStoreUtilities();
	    boolean flag = true;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		Product prod = new Product();
		prod.setProdID(request.getParameter("prodID"));
	
		prod.setProdName(request.getParameter("prodName"));
		prod.setProdType(request.getParameter("prodType"));
		prod.setProdPrice(request.getParameter("prodPrice"));
		prod.setQuantity(request.getParameter("quantity"));
		prod.setRetailer(request.getParameter("retailer"));
		
		
		prod.setManufacturerRebate(request.getParameter("manufacturerRebate"));
		prod.setCondition(request.getParameter("condition"));
		prod.setOnSale(request.getParameter("onSale"));

		ProductCatalog pc = new ProductCatalog();
		pc.setId(prod.getProdID());
		pc.setName(prod.getProdName());
		pc.setProdType(prod.getProdType());
		pc.setCondition(prod.getCondition());
		pc.setManufacturerRebate(prod.getManufacturerRebate());
		pc.setPrice(prod.getProdPrice());
		pc.setImage(prod.getImage());
		pc.setOnSale(prod.getOnSale());
		pc.setQuantity(prod.getQuantity());
		pc.setRetailer(prod.getRetailer());

		if(pc.getProdType().equalsIgnoreCase("phone"))
		{
			PhoneServlet phone = new PhoneServlet();
		phone.hm.put(pc.getId(), pc);
		sqlData.insertProdDetails(prod);
		}
		
		else if(pc.getProdType().equalsIgnoreCase("laptop")){
			LaptopServlet laptop = new LaptopServlet();
		laptop.hm.put(pc.getId(), pc);
		sqlData.insertProdDetails(prod);
		}
		
		else if(pc.getProdType().equalsIgnoreCase("externalStorage")){
		ESServlet es = new ESServlet();
		es.hm.put(pc.getId(), pc);
		sqlData.insertProdDetails(prod);
		}
		else if(pc.getProdType().equalsIgnoreCase("headPhone")){
		HeadphoneServlet phone = new HeadphoneServlet();
		phone.hm.put(pc.getId(), pc);
		sqlData.insertProdDetails(prod);
		}
		else if(pc.getProdType().equalsIgnoreCase("speaker")){
		SpeakerServlet phone = new SpeakerServlet();
		phone.hm.put(pc.getId(), pc);
		sqlData.insertProdDetails(prod);
		}
		else if(pc.getProdType().equalsIgnoreCase("smartWatch")){
		WatchServlet phone = new WatchServlet();
		phone.hm.put(pc.getId(), pc);
		sqlData.insertProdDetails(prod);
		}

		
		
		
		
		
		
		
			
				//sqlData.insertProdDetails(prod);
			
			
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>"
					+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
						"<title>GadgetStation</title>"+
						"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"+
						"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>"+
						"<link rel='stylesheet' href='styles.css' type='text/css' />"+ "</head>");
			out.println("<body>"+"<div id='container'>"+
		    "<header>"+
	    	"<h1><a href='/'>Gadget<span>Station</span></a></h1>"+
	        "<h2>Save money, save time.</h2>"+
	    "</header>");
			out.println("<h1> Welcome</h1>");
			out.println("<br/>");
			out.println("<a href='StoreManagerServlet'> Home Page </a>");
			out.println("</body>");
			out.println("</html>");
		
	
	}catch(Exception e){
		System.out.println("Error Occurred" + e);
	}
	}
	}


