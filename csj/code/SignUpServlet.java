

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	
	HashMap<String, User> users = null;   
   MySqlDataStoreUtilities sqlData = new MySqlDataStoreUtilities();
    boolean flag = true;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		User user = new User();
		//Customer cust1 = new Customer();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setAge(request.getParameter("age"));
		user.setGender(request.getParameter("gender"));
		user.setCountry(request.getParameter("country"));
		user.setAddress(request.getParameter("address"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setUserID(request.getParameter("userID"));
		user.setPassword(request.getParameter("password"));
		user.setUserType(request.getParameter("userType"));
		user.setZipcode(request.getParameter("zipcode"));
		
		

		

		users = sqlData.viewUserDetails();
		
		for(String key : users.keySet())
		{
			if((users.get(key).equals(user.getUserID())))
			{
				response.setContentType("text/html");
				java.io.PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Signup Servlet Result</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>" + "Signup Failed. User already exists!!" + "</h2>");
				out.println("<br/>");
				out.println("<a href='home'> Home Page </a>");
				out.println("</body>");
				out.println("</html>");
				out.close();
				flag = false;
			}
		}
		if(flag)
		 {
			
				sqlData.insertUserDetails(user);
			
			/*String UserData = objCust.getFirstName().trim() + "," + objCust.getLastName().trim() + "," + objCust.getuserId().trim() + ","
					+ objCust.getPassword().trim() + "," + objCust.getPhoneNo().trim()+","+Constants.CUSTOMER;
			writeFile(Constants.UserDetailFile, UserData);*/
			
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
			out.println("<h1> Welcome, " + user.getFirstName().trim() + "</h1>");
			out.println("<br/>");
			out.println("<a href='index.html'> Home Page </a>"+"<a href='login.html'> Login </a><a href='SalesmanServlet'> Click Here </a>");
			out.println("</body>");
			out.println("</html>");
		}
	
	}catch(Exception e){
		System.out.println("Error Occurred" + e);
	}
	
	}
	}


