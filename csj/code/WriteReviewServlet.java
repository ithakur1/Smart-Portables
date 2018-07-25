

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/WriteReviewServlet")
public class WriteReviewServlet extends HttpServlet {
	public HashMap<String, ProductCatalog> hm = null;
	Review review = null;	
	ProductCatalog pc = null;
    
	public WriteReviewServlet() {
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productID = request.getParameter("id");
		System.out.println(productID);
		String category = null;
		
		if(productID.startsWith("ph"))
		{
			category = "phone";
			hm = Hashmapping.isPhoneCatalog();
		}
		if(productID.startsWith("lp"))
		{
			category = "Laptop";
			hm = Hashmapping.isLaptopCatalog();
		}
		if(productID.startsWith("sw"))
		{
			category = "Smart Watch";
			hm = Hashmapping.isWatchCatalog();
		}
		if(productID.startsWith("sp"))
		{
			category = "Speaker";
			hm = Hashmapping.isSpeakerCatalog();
		}
		if(productID.startsWith("hp"))
		{
			category = "Headphones";
			hm = Hashmapping.isHeadphoneCatalog();
		}
		if(productID.startsWith("es"))
		{
			category = "External Storage";
			hm = Hashmapping.isExtStoreCatalog();
		}
		
		pc = hm.get(productID);
		PrintWriter out = response.getWriter();	
		OrderPlacement helper = new OrderPlacement(request, out);
		helper.writeReviewContent(pc,category);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			review  = new Review();
			
			
			review.setProductModelName(request.getParameter("productmodelname"));
			
			review.setCategory(request.getParameter("category"));
			review.setProductPrice(request.getParameter("productprice"));
			review.setRetailerName(request.getParameter("retailername"));
			review.setRetailerZip(request.getParameter("retailerzip"));
			review.setRetailerCity(request.getParameter("retailercity"));
			review.setRetailerState(request.getParameter("retailerstate"));
			review.setProductOnSale(request.getParameter("productonsale"));
			review.setUserID(request.getParameter("userid"));
			review.setManufacturerRebate(request.getParameter("manufacturerrebate"));
			review.setUserOccupation(request.getParameter("useroccupation"));
			review.setReviewRating(request.getParameter("reviewrating"));
			review.setUserAge(request.getParameter("userage"));
			review.setManufacturerName(request.getParameter("manufacturername"));
			review.setReviewText(request.getParameter("reviewtext"));
			review.setUserGender(request.getParameter("usergender"));
			review.setReviewDate(request.getParameter("reviewdate"));
			
			System.out.println(review.getCategory());
			
		MongoDBDataStoreUtilities.insertReview(review);
						
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Review Servlet Result</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>" + "Review Submitted Successfully." + "</h2>");
			out.println("<br/>");
			out.println("<a href='CustomerServlet'> Home Page </a>");			
			out.println("</body>");
			out.println("</html>");
			out.close();
		}		
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}

}
