

public class ItemOrdered {
	private String name;
	private String price;
	private String image;
	private String retailer;
	private String itemId;
	
	public ItemOrdered(String itemId, String name, String price, String image, String retailer){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemID) {
		this.itemId = itemID;
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

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
}
