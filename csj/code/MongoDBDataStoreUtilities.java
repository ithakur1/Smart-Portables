


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoDBDataStoreUtilities {
	
	static DBCollection myReviews;
	Review rv1 = null;
	
	public static void getConnection()
	{
		MongoClient mongo = new MongoClient("localhost", 27017);
		
		DB db = mongo.getDB("CustomerReviews");
		myReviews = db.getCollection("myReviews");
		System.out.println("Collection myNewReviews selected successfully");
	}
	
	public static void insertReview(Review rv)
	{
		try
		{
			if(rv != null)
			{
				System.out.println(rv.getProductModelName());
//				getConnection();
				// Connect to Mongo DB
				MongoClient mongo = new MongoClient("localhost", 27017);
							
				// If database doesn't exists, MongoDB will create it for you
				DB db = mongo.getDB("CustomerReviews");
				
				// If the collection does not exists, MongoDB will create it for you
				// DBCollection myReviews = db.getCollection("myReviews");
				// System.out.println("Collection myReviews selected successfully");
				
				DBCollection myReviews = db.getCollection("myNewReviews");
				System.out.println("Collection myNewReviews selected successfully");
				
				BasicDBObject doc = new BasicDBObject("title", "myReviews").
						append("productmodelname", rv.getProductModelName()).
						append("category", rv.getCategory()).
						append("productprice", rv.getProductPrice()).
						append("retailername", rv.getRetailerName()).
						append("retailerzip", rv.getRetailerZip()).
						append("retailercity", rv.getRetailerCity()).
						append("retailerstate", rv.getRetailerState()).
						append("productonsale", rv.getProductOnSale()).
						append("userid", rv.getUserID()).
						append("manufacturerrebate", rv.getManufacturerRebate()).
						append("useroccupation", rv.getUserOccupation()).
						append("reviewrating", rv.getReviewRating()).
						append("userage", rv.getUserAge()).
						append("manufacturername", rv.getManufacturerName()).
						append("reviewtext", rv.getReviewText()).
						append("usergender", rv.getUserGender()).
						append("reviewdate", rv.getReviewDate());

				myReviews.insert(doc);
				System.out.println("Document inserted successfully");
			}
		}
		catch(MongoException ex)
		{
			 ex.printStackTrace();
		}
	}

	/*public static void insertReview(String productName, String productCategory, int productPrice, String retailerName,
			String retailerZip, String retailerCity, String retailerState, String productSale, String manufactureName,
			String manufactureRebate, String userName, int userAge, String userGender, String userOccu,
			int reviewRating, String reviewDate, String reviewText) {
		

			MongoClient mongo;
			
			
		     	
		mongo = new MongoClient("localhost", 27017);
				
			

		DB db = mongo.getDB("CustomerReviews");
					
					DBCollection myReviews = db.getCollection("myReviews");

					BasicDBObject doc = new BasicDBObject("title", "MongoDB")
							.append("ProductModelName", productName)
							.append("ProductCategory",productCategory)
							.append("productPrice", productPrice)
							.append("RetailerName", retailerName)
							.append("RetailerZip", retailerZip)
							.append("RetailerCity", retailerCity)
							.append("RetailerState", retailerState)
							.append("ProductOnSale", productSale)
							.append("ManufactureName",manufactureName)
							.append("ManufactureRebate",manufactureRebate)
							.append("UserID", userName)
							.append("UserAge",userAge)
							.append("UserGender", userGender) 
							.append("UserOccupation", userOccu)
							.append("ReviewRating", reviewRating)
							.append("ReviewDate", reviewDate)
							.append("ReviewText", reviewText);

					myReviews.insert(doc);
		             return;
		}*/
		
	}
	

