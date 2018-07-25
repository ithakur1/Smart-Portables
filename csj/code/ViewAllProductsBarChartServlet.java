

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

import com.google.gson.Gson;

@WebServlet("/ViewAllProductsBarChartServlet")
public class ViewAllProductsBarChartServlet extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MySqlDataStoreUtilities ms = new MySqlDataStoreUtilities();
		
			ArrayList<Product> prod = null;
			try {
				prod = ms.viewProductsBarChart();
			} catch (Exception e){
			}	
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(prod));
	}

	

}
