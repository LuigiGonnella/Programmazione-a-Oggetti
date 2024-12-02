package warehouse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
	private String code,description;
	private int quantity;

	
	
	public Product(String code, String description) {
		this.code=code;
		this.description=description;
		
	}

	public String getCode(){
		return this.code;
	}

	public String getDescription(){
		return this.description;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
		
	}

	public void decreaseQuantity(){
		this.quantity--;
		
	}

	public int getQuantity(){
		return this.quantity;
	}

	

	public List<Order> pendingOrders(){
		// TODO: completare
		return null;
	}

	public void addQuantity(int quantityOrd) {
		
	}

	List<Supplier> forns = new ArrayList<>();
	
	
	public void addSupplier(String codeS) {
		
	}
	
	public List<Supplier> suppliers(){
		return this.forns;
	}
	
}
