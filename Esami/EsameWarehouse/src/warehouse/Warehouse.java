package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Warehouse {
	
	
	List<Product> prods = new ArrayList<>();
	public Product newProduct(String code, String description){
		Product p = new Product(code,description);
		prods.add(p);
		return p;
	}
	
	public Collection<Product> products(){
		return this.prods;
	}

	public Product findProduct(String code){
		return this.prods.stream().filter(s->s.getCode().equals(code)).collect(Collectors.toList()).get(0);
	}

	List<Supplier> forns = new ArrayList<>();

	public Supplier newSupplier(String code, String name){
		Supplier supp = new Supplier(code,name);
		forns.add(supp);

		return supp;
	}
	
	public Supplier findSupplier(String code){
		return null;
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		return null;
	}

	public Order findOrder(String code){
		return null;
	}
	
	public List<Order> pendingOrders(){
		return null;
	}

	public Map<String,List<Order>> ordersByProduct(){
	    return null;
	}
	
	public Map<String,Long> orderNBySupplier(){
	    return null;
	}
	
	public List<String> countDeliveredByProduct(){
	    return null;
	}
}
