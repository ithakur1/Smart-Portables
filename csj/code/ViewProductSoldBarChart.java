

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/ViewProductSoldBarChart")
public class ViewProductSoldBarChart extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MySqlDataStoreUtilities ms=new MySqlDataStoreUtilities();
		
			ArrayList<ItemOrdered> io = null;
			try {
				io = ms.viewProductOnSaleBarChart();
			} catch (Exception e){
			}	
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(io));
	}

}
