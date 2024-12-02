package restaurantChain;

public class Menu {
	String name;
	double price;
	
	public Menu(String name, double price){
		this.name = name;
		this.price = price;
	}	
	public double getPrice(){
		return price;
	}
}
