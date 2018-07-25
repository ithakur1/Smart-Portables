

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
	
	private String id;	
	private String name;
	private String price;
	private String image;
	private String condition;
	private String retailer;
	private String prodType;
	private String manufacturerRebate;
	private String quantity;
	private String onSale;
	//private List<String> accessories;
	
	public ProductCatalog(){
        //accessories=new ArrayList<String>();
    }
	public ProductCatalog(String id, String name, String price, String image, String condition, String retailer,
			String prodType, String manufacturerRebate, String quantity, String onSale) {
	
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.condition = condition;
		this.retailer = retailer;
		this.prodType = prodType;
		this.manufacturerRebate = manufacturerRebate;
		this.quantity = quantity;
		this.onSale = onSale;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}
	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOnSale() {
		return onSale;
	}
	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}
		

	
	

}
