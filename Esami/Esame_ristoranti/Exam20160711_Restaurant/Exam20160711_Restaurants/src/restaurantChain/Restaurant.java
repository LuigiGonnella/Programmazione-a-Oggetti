package restaurantChain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
	private String name;
	private Integer numtavoli;
	private Integer disponibili;
	private Double tot = 0.0;
	private Integer nonaccolte =0;

	public Integer getNonaccolte() {
		return nonaccolte;
	}

	public void setNonaccolte(Integer nonaccolte) {
		this.nonaccolte += nonaccolte;
	}

	public Double getTot() {

		return tot;
	}

	public void setTot(Double tot) {
		this.tot += tot;
	}

	public Integer getDisponibili() {
		return disponibili;
	}

	public void setDisponibili(Integer disponibili) {
		this.disponibili = disponibili;
	}

	public void setNumtavoli(Integer numtavoli) {
		this.numtavoli = numtavoli;
	}

	public Integer getNumtavoli() {
		return this.numtavoli;
	}

	public Restaurant(String name, int numtavoli) {
		this.name=name;
		this.numtavoli=numtavoli;
		this.disponibili=numtavoli;
	}
	
	public String getName(){
		return this.name;
	}
	public class Menu {
		private String name;
		public String getName() {
			return name;
		}
		private Double price;
		public Double getPrice() {
			return price;
		}
		public Menu(String name, double price) {
			this.name=name;
			this.price=price;
		}
	}
	List<Menu> menus = new ArrayList<>();
	public void addMenu(String name, double price) throws InvalidName{
		if (menus.stream().filter(s->s.getName().equals(name)).count()>0) {
			throw new InvalidName();
		}
		menus.add(new Menu(name, price));
		
	}

	public class Prenotazioni {
		private String status;
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		private Integer numtavoli;
		public Integer getNumtavoli() {
			return numtavoli;
		}

		public void setNumtavoli(Integer numtavoli) {
			this.numtavoli = numtavoli;
		}

		private String name;
		public String getName() {
			return name;
		}

		private Integer num;

		public Integer getNum() {
			return num;
		}

		public Prenotazioni(String name, int num) {
			this.name=name;
			this.num=num;
		}
	}
	List<Prenotazioni> prens = new ArrayList<>();
	
	public int reserve(String name, int persons) throws InvalidName{
		if (prens.stream().filter(s->s.getName().equals(name)).count()>0) {
			throw new InvalidName();
		}
		Prenotazioni p = new Prenotazioni(name, persons);
		prens.add(p);

		Integer numtavoli= persons/4;
		if (persons%4!=0) {
			numtavoli+=1;
		}
		if (this.getDisponibili()<numtavoli) {
			p.setStatus("rifiutata");
			this.setNonaccolte(persons);
			return 0;
		}
		p.setStatus("accettata");
		p.setNumtavoli(numtavoli);
		this.setDisponibili(this.getDisponibili()-numtavoli);
		return numtavoli;
	}
	
	public int getRefused(){
		return this.getNonaccolte();
	}
	
	public int getUnusedTables(){
		return this.getDisponibili();
	}
	

	public class Order{
		private Double income = 0.0;
		public Double getIncome() {
			return income;
		}

		public void setIncome(Double income) {
			this.income = income;
		}
		private String Status = "unpaid";
		public String getStatus() {
			return Status;
		}

		public void setStatus(String status) {
			Status = status;
		}
		private String name;
		public String getName() {
			return name;
		}
		private List<Menu> mens=new ArrayList<>();
		public List<Menu> getMens() {
			return mens;
		}

		public Order(String name, String... menu) {
			this.name=name;
			for (String str:menu) {
				mens.add(menus.stream().filter(s->s.getName().equals(str)).collect(Collectors.toList()).get(0));
			}
		}
	}
	List<Order> ords = new ArrayList<>();
	public boolean order(String name, String... menu) throws InvalidName{
		if(prens.stream().filter(s->s.getName().equals(name) && s.getStatus().equals("accettata")).count()==0) {
			throw new InvalidName();
		}
		Prenotazioni pren = prens.stream().filter(s->s.getName().equals(name)).collect(Collectors.toList()).get(0);

		if (menu.length<pren.getNum()) {
			return false;
		}

		for (String m : menu) {
			if(!this.menus.stream().map(s->s.getName()).collect(Collectors.toList()).contains(m)) {
				throw new InvalidName();
			}
		}

		ords.add(new Order(name, menu));

		return true;
	}
	
	public List<String> getUnordered(){
		return this.prens.stream().filter(s->s.getStatus().equals("accettata") && !ords.stream().map(k->k.getName()).collect(Collectors.toList()).contains(s.getName())).map(s->s.getName()).sorted().collect(Collectors.toList());
	}
	
	public double pay(String name) throws InvalidName{
		if(prens.stream().filter(s->s.getName().equals(name) && s.getStatus().equals("accettata")).count()==0) {
			throw new InvalidName();
		}

		if(ords.stream().filter(s->s.getName().equals(name)).count()==0) {
			return 0.0;
		}

		double spesa = 0.0;

		for (Menu m : ords.stream().filter(s->s.getName().equals(name)).collect(Collectors.toList()).get(0).getMens()) {
			spesa+=m.getPrice();
		}
		this.setTot(spesa);
		ords.stream().filter(s->s.getName().equals(name)).collect(Collectors.toList()).get(0).setStatus("paid");
		return spesa;
	}
	
	public List<String> getUnpaid(){
		return this.ords.stream().filter(s->s.getStatus().equals("unpaid")).map(s->s.getName()).sorted().collect(Collectors.toList());
	}
	
	public double getIncome(){
		return this.getTot();
	}

}
