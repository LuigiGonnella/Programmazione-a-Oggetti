package groups;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;



public class GroupHandling {
//R1
public class Product {
	private String productTypeName;
	public String getProductTypeName() {
		return productTypeName;
	}

	private List<String> forns = new ArrayList<>();

	public List<String> getForns() {
		return forns;
	}

	public Product(String productTypeName, String... supplierNames) {
		this.productTypeName=productTypeName;
		for (String forn : supplierNames) {
			forns.add(forn);
		}
	}
}

List<Product> prodotti = new ArrayList<>();
List<String> fornitori = new ArrayList<>();
	public void addProduct (String productTypeName, String... supplierNames) 
			throws GroupHandlingException {
				GroupHandlingException e = new GroupHandlingException();

				if (prodotti.stream().filter(s->s.getProductTypeName().equals(productTypeName)).count()>0)  {
					throw e;
				}

				prodotti.add(new Product(productTypeName, supplierNames));

				for (String sup : supplierNames) {
					fornitori.add(sup);
				}

				this.fornitori=this.fornitori.stream().distinct().collect(Collectors.toList());
	}
	
	public List<String> getProductTypes (String supplierName) {
		return this.prodotti.stream().filter(s->s.getForns().contains(supplierName)).map(s->s.getProductTypeName()).sorted().collect(Collectors.toList());
	}
	
//R2
	public class Group {
		private List<String> forns = new ArrayList<>();
		public List<String> getForns() {
			return forns;
		}

		private String name;
		public String getName() {
			return name;
		}

		private Product productType;
		public Product getProductType() {
			return productType;
		}

		private List<String> customers = new ArrayList<>();

		public List<String> getCustomers() {
			return customers;
		}

		public Group(String name, String productName, String... customerNames) {
			this.name=name;
			this.productType=prodotti.stream().filter(s->s.getProductTypeName().equals(productName)).collect(Collectors.toList()).get(0);
			for (String str : customerNames) {
				this.customers.add(str);
			}
		}
	}	

	List<Group> gruppi = new ArrayList<>();
	public void addGroup (String name, String productName, String... customerNames) 
			throws GroupHandlingException {

				GroupHandlingException e = new GroupHandlingException();

				if (prodotti.stream().filter(s->s.getProductTypeName().equals(productName)).count()==0)  {
					throw e;
				}

				if (gruppi.stream().filter(s->s.getName().equals(name)).count()>0)  {
					throw e;
				}

				gruppi.add(new Group(name, productName, customerNames));


	}
	
	public List<String> getGroups (String customerName) {
        return this.gruppi.stream().filter(s->s.getCustomers().contains(customerName)).map(s->s.getName()).sorted().collect(Collectors.toList());
	}

//R3
	public void setSuppliers (String groupName, String... supplierNames)
			throws GroupHandlingException {

				GroupHandlingException e = new GroupHandlingException();

				if (gruppi.stream().filter(s->s.getName().equals(groupName)).count()==0)  {
					throw e;
				}

				Product pr = this.gruppi.stream().filter(s->s.getName().equals(groupName)).collect(Collectors.toList()).get(0).getProductType();

				for (String str : supplierNames) {
					if (!this.fornitori.contains(str)) {
						throw e;
					}

					if (!pr.getForns().contains(str)) {
						throw e;
					}
				}

				for (String str : supplierNames) {
					this.gruppi.stream().filter(s->s.getName().equals(groupName)).collect(Collectors.toList()).get(0).getForns().add(str);
				}

				



	}

	public class Bid {
		private Group gruppo;
		public Group getGruppo() {
			return gruppo;
		}

		private String forn;
		public String getForn() {
			return forn;
		}

		private Integer price;

		public Integer getPrice() {
			return price;
		}

		public Bid(String groupName, String supplierName, int price) {
			this.gruppo=gruppi.stream().filter(s->s.getName().equals(groupName)).collect(Collectors.toList()).get(0);

			this.forn=supplierName;

			this.price=price;
		}
	}
	
	List<Bid> offerte = new ArrayList<>();
	public void addBid (String groupName, String supplierName, int price)
			throws GroupHandlingException {
				GroupHandlingException e = new GroupHandlingException();

				if (!this.gruppi.stream().filter(s->s.getName().equals(groupName)).collect(Collectors.toList()).get(0).getForns().contains(supplierName)) {
					throw e;
				}

				offerte.add(new Bid(groupName, supplierName, price));
	}
	
	public String getBids (String groupName) {

		return offerte.stream().filter(s->s.getGruppo().getName().equals(groupName)).sorted(Comparator.comparing((Bid s)->s.getPrice()).thenComparing(s->s.getForn())).map(s->s.getForn()+":"+s.getPrice()).collect(Collectors.joining(","));
	}
	
	
//R4	
	public class Voto {
		private Group gruppo;
		public Group getGruppo() {
			return gruppo;
		}

		private String forn;
		public String getForn() {
			return forn;
		}

		private String customer;

		public String getCustomer() {
			return customer;
		}

		public Voto(String groupName, String customerName, String supplierName) {
			this.gruppo=gruppi.stream().filter(s->s.getName().equals(groupName)).collect(Collectors.toList()).get(0);
			this.customer=customerName;
			this.forn=supplierName;
		}
	}

	List<Voto> voti = new ArrayList<>();
	public void vote (String groupName, String customerName, String supplierName)
			throws GroupHandlingException {
				GroupHandlingException e = new GroupHandlingException();

				if (!gruppi.stream().filter(s->s.getName().equals(groupName)).collect(Collectors.toList()).get(0).getCustomers().contains(customerName)) {
					throw e ;
				}

				if(offerte.stream().filter(s->s.getGruppo().getName().equals(groupName) && s.getForn().equals(supplierName)).count()==0) {
					throw e;
				}

				voti.add(new Voto(groupName, customerName, supplierName));

	}
	
	public String  getVotes (String groupName) {
		Map<String,Long> m = this.voti.stream().filter(s->s.getGruppo().getName().equals(groupName)).collect(Collectors.groupingBy(s->s.getForn(),Collectors.counting()));

        return m.entrySet().stream().map(s->s.getKey()+":"+s.getValue()).sorted().collect(Collectors.joining(","));
	}
	
	public String getWinningBid (String groupName) {
		String[] wins = getVotes(groupName).split(",");
		Integer max =-1;
		List<String> maxs = new ArrayList<>();

		Integer min;

		String ret=null;

		for (String off : wins) {
			if (Integer.valueOf(off.split(":")[1])>=max) {
				max=Integer.valueOf(off.split(":")[1]);
				maxs.add(off);
			}
		}

		if (maxs.size()==1) {
			return maxs.get(0);
		}

		min= offerte.stream().filter(s->s.getGruppo().getName().equals(groupName) && s.getForn().equals(maxs.get(0).split(":")[0])).collect(Collectors.toList()).get(0).getPrice(); 

		for (String off : maxs) {
			Integer pr = offerte.stream().filter(s->s.getGruppo().getName().equals(groupName) && s.getForn().equals(off.split(":")[0])).collect(Collectors.toList()).get(0).getPrice();

			if(pr<min) {
				min=pr;
				ret=off;
			}
		}

        return ret;
	}
	
//R5
	public SortedMap<String, Integer> maxPricePerProductType() { //serve toMap
		Map<String, Optional<Integer>> m = this.offerte.stream().collect(Collectors.groupingBy(s->s.getGruppo().getProductType().getProductTypeName(), Collectors.mapping(s->s.getPrice(), Collectors.maxBy(Integer::compareTo))));



        return m.entrySet().stream().collect(TreeMap::new, (map,s)->map.put(s.getKey(), s.getValue().get()), TreeMap::putAll);
	}
	
	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
		Map<String, Long> m = this.offerte.stream().collect(Collectors.groupingBy(s->s.getForn(), Collectors.counting()));

		Map<Integer, List<String>> inv = m.entrySet().stream().collect(Collectors.groupingBy(s->s.getValue().intValue(), Collectors.mapping(s->s.getKey(), Collectors.toList())));

        return inv.entrySet().stream().collect(()->new TreeMap<>(Comparator.reverseOrder()), (map,s)->map.put(s.getKey(), s.getValue().stream().sorted().collect(Collectors.toList())), TreeMap::putAll);
	}
	
	public SortedMap<String, Long> numberOfCustomersPerProductType() {
		Map<String, Long> m = this.gruppi.stream().collect(Collectors.groupingBy(s->s.getProductType().getProductTypeName(), Collectors.summingLong(s->s.getCustomers().size())));
        return m.entrySet().stream().collect(TreeMap::new, (map,s)->map.put(s.getKey(), s.getValue()), TreeMap::putAll);
	}
	
}
