



public class Accessory {
	
	private String id;	
	private String name;
	private String price;
	private String image;
	private String condition;
	private String retailer;
	//private List<String> accessories;
	
	public Accessory(){
        //accessories=new ArrayList<String>();
    }
		

	public Accessory(String id, String name, String price, String image, String condition, String retailer) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.condition = condition;
		this.retailer = retailer;
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

	/*public List<String> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;*/
	
	
	
	

}
