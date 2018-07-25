
public class Product {
	
	private String prodID;
	private String prodName;
	private String prodType;
	private String prodPrice;
	private String quantity;
	private String retailer;
	private String manufacturerRebate;
	private String condition;
	private String onSale;
	private String image;
	
	
	/*	private String id;	
	private String name;
	private String price;
	private String image;
	private String condition;
	private String retailer;
	private String prodType;
	private String manufacturerRebate;
	private String quantity;
	private String onSale;*/
	
	public Product(String prodID, String prodName, String prodType, String prodPrice, String quantity, String retailer,
			String manufacturerRebate, String condition, String onSale, String img) {
		
		this.prodID = prodID;
		this.prodName = prodName;
		this.prodType = prodType;
		this.prodPrice = prodPrice;
		this.quantity = quantity;
		this.retailer = retailer;
		this.manufacturerRebate = manufacturerRebate;
		this.condition = condition;
		this.onSale = onSale;
		this.image = img;
	}

	public Product() {
	
	}

	public String getProdID() {
		return prodID;
	}

	public void setProdID(String prodID) {
		this.prodID = prodID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String img) {
		this.image = img;
	}
	
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getManufacturerRebate() {
		return manufacturerRebate;
	}

	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOnSale() {
		return onSale;
	}

	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}
	
	
	
	
	
	
	

}
