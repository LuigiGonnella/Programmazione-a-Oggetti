package warehouse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class Supplier {
	String code,name;
	
	
	public Supplier(String code, String name) {
		this.code=code;
		this.name=name;
		
	}

	public String getCodice(){
		return this.code;
	}

	public String getNome(){
		return this.name;
	}
	

	List<Product> prods = new ArrayList<>();
	
	public void newSupply(Product product){
		prods.add(product);
		
	}
	
	public List<Product> supplies(){
		return  this.prods.stream().sorted(Comparator.comparing(s->s.getDescription())).collect(Collectors.toList());
	}
}
	


