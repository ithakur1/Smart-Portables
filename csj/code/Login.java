

import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	Connection conn = null;
	HashMap<String, User> users = null;
	HashMap<String, ProductCatalog> hmPrdCat = new HashMap<String, ProductCatalog>();
	User user = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ProductCatalog pc = null;
	Statement stmt = null;
	boolean flag;
	String passwd;
	String usType;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			try {
				commonUtility(request, response);
			} catch (InstantiationException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
//		String userid = request.getParameter("username");
//		userid = userid.trim();
//       
//		String uPassword = request.getParameter("password");
//		uPassword = uPassword.trim();
//		
//		PrintWriter out = response.getWriter();
//
//		if(userid != null && uPassword != null)
//		{			
//			ServletContext sc=request.getSession().getServletContext();
//			
//			BufferedReader br=new BufferedReader(new FileReader("C:/Users/ithak/workspace/Demo2/WebContent/WEB-INF/UserDetails.txt"));
//			int flagValue=0;				
//			String Line;
//			
//			
//			while((Line = br.readLine())!=null)
//			{
//				
//				String[] strDetails = Line.split(",");
//			 
//				if((userid.equals(strDetails[6])) && (uPassword.equals(strDetails[7])))
//				{
//					flagValue=1;
//					break;
//				}
//				else
//				{
//					flagValue=0;
//				}
//			}
//			if(flagValue==1)
//			{
//				if(userid.equals("storemanager"))
//				{						
//					HttpSession session=request.getSession(); 
//					session.setAttribute("userid",userid); 
//					response.sendRedirect("StoreManagerServlet");
//				}
//				else if(userid.equals("salesman"))
//				{
//					HttpSession session=request.getSession();
//					session.setAttribute("userid",userid);
//					response.sendRedirect("SalesmanServlet");
//				}
//				else
//				{	
//					HttpSession session=request.getSession();
//					session.setAttribute("userid",userid);
//					response.sendRedirect("CustomerServlet");
//				}
//			}
//			else
//			{					
//				RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
//				rd.forward(request, response);
//				out.println("Invalid Username or Password");
//			}   
//		
//		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	{
		try {
			commonUtility(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	void commonUtility(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		String userid = request.getParameter("username");
		userid = userid.trim();
       
		String uPassword = request.getParameter("password");
		uPassword = uPassword.trim();
		
		String uType = request.getParameter("userType");
		uType = uType.trim();
		
	
		PrintWriter out = response.getWriter();
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewadb","root","tiger");
		stmt = conn.createStatement();			
		String view = "SELECT * FROM users";
		rs = stmt.executeQuery(view);
		
		 while(rs.next())
				
			{
			
			  if(rs.getString(9).equals(userid)){
	
	          passwd = rs.getString(10);
	          usType = rs.getString(11);
			}	    
		}	
		 
		 if(uPassword.equals(passwd))
			{
				flag=true;
				
			}   
			
			else{
				String msg = "Login Failure! Username or password is incorrect.";
				out.println("<html>");
				out.println("<head>");
				out.println("</head>");
				out.println("<body>");
				out.println("<hr>");
				out.println("<h2 align='center'>" + msg + "</h2>");
				out.println("<div style='text-align:center'><a href='index.html'>Home</a></div>");
				out.println("</body>");
				out.println("</html>");
			}
		
		if(flag)
			{
				if(usType.equals("storeManager"))
				{						
					HttpSession session=request.getSession(); 
					session.setAttribute("userid",userid); 
					response.sendRedirect("StoreManagerServlet");
				}
				else if(usType.equals("salesMan"))
				{
					HttpSession session=request.getSession();
					session.setAttribute("userid",userid);
					response.sendRedirect("SalesmanServlet");
				}
				else if(usType.equals("customer"))
				{	
					HttpSession session=request.getSession();
					session.setAttribute("userid",userid);
					response.sendRedirect("CustomerServlet");
				}
			}
			else
			{					
				RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
				rd.forward(request, response);
				out.println("Invalid Username or Password");
			}   
		
		}
	}

	


