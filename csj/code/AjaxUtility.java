import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.*;

@WebServlet("/AjaxUtility")
public class AjaxUtility extends HttpServlet {

    private ServletContext context;    
   

    
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
HashMap<String, ProductCatalog> products = Hashmapping.getAllProducts();
        
		String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();
						
        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            
            if (!targetId.equals("")) {

                Iterator it = products.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                    ProductCatalog product = products.get(id);

                    if (product.getName().toLowerCase().startsWith(targetId)) {

                        sb.append("<product>");
                        sb.append("<productid>" + product.getId() + "</productid>");
                        sb.append("<productname>" + product.getName() + "</productname>");                       
                        sb.append("</product>");
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {
			
			if(targetId.toLowerCase().startsWith("ph"))
			{
				response.sendRedirect("PhoneServlet?id="+targetId); 
			}
			else if(targetId.toLowerCase().startsWith("es"))
			{
				response.sendRedirect("ESServlet?id="+targetId); 
			}
			else if(targetId.toLowerCase().startsWith("lp"))
			{
				response.sendRedirect("LaptopServlet?id="+targetId); 
			}
			else if(targetId.toLowerCase().startsWith("sp"))
			{
				response.sendRedirect("SpeakerServlet?id="+targetId); 
			}
			else if(targetId.toLowerCase().startsWith("sw"))
			{
				response.sendRedirect("WatchServlet?id="+targetId); 
			}  
			else if(targetId.toLowerCase().startsWith("hp"))
			{
				response.sendRedirect("HeadphoneServlet?id="+targetId); 
			}             
        }
	
    }
}
