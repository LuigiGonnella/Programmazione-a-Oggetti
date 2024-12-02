package restaurantChain;

public class Reservation {
	String name;
	int persons;
	double bill;
	boolean paid, ordered;
	
	public Reservation(String name, int persons){
		this.name = name;
		this.persons = persons;
		paid = ordered = false;
		bill = 0.0;
	}
	public String getName(){
		return name;
	}
	public int getPersons(){
		return persons;
	}
	public void setBill(double b){
		bill = b;
	}
	public double getBill(){
		return bill;
	}
	public void setPaid(){
		paid = true;
	}
	public boolean getPaid(){
		return paid;
	}
	public void setOrdered(){
		ordered = true;
	}
	public boolean getOrdered(){
		return ordered;
	}
}
