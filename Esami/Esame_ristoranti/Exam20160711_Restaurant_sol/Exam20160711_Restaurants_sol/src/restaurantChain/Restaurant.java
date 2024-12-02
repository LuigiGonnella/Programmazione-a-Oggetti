package restaurantChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Restaurant {
	String name;
	int totalTables;
	int availableTables;
	int refused;
	double income;
	Map<String,Menu> menus;
	Map<String,Reservation> reservations;
	
	public Restaurant( String name, int tables){
		this.name = name;
		totalTables = availableTables = tables;
		refused = 0;
		income = 0.0;
		menus = new HashMap<>();
		reservations = new HashMap<>();
	}
	public void addMenu(String name, double price) throws InvalidName{
		if(menus.get(name)!=null) throw new InvalidName();
		menus.put(name, new Menu(name,price));
	}
	public int reserve(String name, int persons) throws InvalidName{
		if(reservations.get(name)!= null) throw new InvalidName();
		int tables = (int) Math.ceil(persons/4.);
		if(availableTables < tables){
			refused += persons;
			return 0;
		}		
		availableTables -= tables;
		Reservation r = new Reservation(name,persons);
		reservations.put(name, r);
		return tables;
	}
	public boolean order(String name, String... menu) throws InvalidName{
		Reservation r = reservations.get(name);
		if(r==null) throw new InvalidName();
		if(r.getPersons() > menu.length) return false;
		double bill = 0.0;
		for(String n : menu){
			Menu m = menus.get(n);
			if(m==null) throw new InvalidName();
			bill += m.getPrice();
		}
		r.setBill(bill);
		r.setOrdered();
		return true;
	}
	public double pay(String name) throws InvalidName{
		Reservation r = reservations.get(name);
		if(r==null) throw new InvalidName();
		if(!r.getOrdered()) return 0.0;
		r.setPaid();
		double bill = r.getBill();
		income += bill;
		return bill;
	}
	public List<String> getUnordered(){
		return reservations.values()
				.stream()
				.filter(r -> !r.getOrdered())
				.map(Reservation::getName)
				.sorted()
				.collect(Collectors.toList());
	}
	public List<String> getUnpaid(){
		return reservations.values()
				.stream()
				.filter(Reservation::getOrdered)
				.filter(r -> !r.getPaid())
				.map(Reservation::getName)
				.sorted()
				.collect(Collectors.toList());
	}
	public String getName(){
		return name;
	}
	public double getIncome(){
		return income;
	}
	public int getRefused(){
		return refused;
	}
	public int getUnusedTables(){
		return availableTables;
	}
}
