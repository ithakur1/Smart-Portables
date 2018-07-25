

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		MySqlDataStoreUtilities objSql = new MySqlDataStoreUtilities();	

		if(productName.toLowerCase().startsWith("ph"))
					{
						Hashmapping.phoneCatalog.remove(productName);
					}
					else if(productName.toLowerCase().startsWith("hp"))
					{
						 Hashmapping.headPhoneCatalog.remove(productName);
					}
					else if(productName.toLowerCase().startsWith("lp"))
					{
						Hashmapping.laptopCatalog.remove(productName);
					}
					else if(productName.toLowerCase().startsWith("sp"))
					{
						 Hashmapping.speakerCatalog.remove(productName);
					}
					else if(productName.toLowerCase().startsWith("es"))
					{
						 Hashmapping.extStoreCatalog.remove(productName);
					}
					else if(productName.toLowerCase().startsWith("sw"))
					{
						 Hashmapping.watchCatalog.remove(productName);
					}
		
		Hashmapping.deleteProduct(prod.getProdName());

					
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Product Deleted</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>" + "Product Deleted Successfully." + "</h2>");
		out.println("<br/>");
		out.println("<a href='StoreManagerServlet'> Home Page </a>");			
		out.println("</body>");
		out.println("</html>");	
	}

}
