package managingProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PropertyManager {
	public class Building {
		private String id;
		public String getId() {
			return id;
		}

		private Integer num;

		public Integer getNum() {
			return num;
		}

		public Building(String building, int n) {
			this.id=building;
			this.num=n;
		}
	}

	List<Building> builds = new ArrayList<>();
	public void addBuilding(String building, int n) throws PropertyException {
		PropertyException e = new PropertyException();
		if (builds.stream().filter(s->s.getId().equals(building)).count()>0) {
			throw e;
		}
		if(n<1 || n>100) {
			throw e;
		}
		
		builds.add(new Building(building, n));
		
	}

	public class Appartamento {
		private Building b;
		public Building getB() {
			return b;
		}

		private Integer num;

		public Integer getNum() {
			return num;
		}

		public Appartamento(Building b, Integer num) {
			this.b=b;
			this.num=num;
		}
	}

	public class Owner {
		private String id;
		public String getId() {
			return id;
		}

		private List<Appartamento> appartamenti = new ArrayList<>();

		

		public List<Appartamento> getAppartamenti() {
			return appartamenti;
		}



		public Owner(String owner, String... apartments) {
			this.id=owner;
			for (String str : apartments) {
				Building b = builds.stream().filter(s->s.getId().equals(str.split(":")[0])).collect(Collectors.toList()).get(0);
				Integer num = Integer.valueOf(str.split(":")[1]);
				Appartamento app = new Appartamento(b, num);
				appartamenti.add(app);
				apps.add(app);
			}
		}
	}

	List<Owner> owns = new ArrayList<>();
	List<Appartamento> apps = new ArrayList<>();
	public void addOwner(String owner, String... apartments) throws PropertyException {
		PropertyException e = new PropertyException();
		if (owns.stream().filter(s->s.getId().equals(owner)).count()>0) {
			throw e;
		}
		for (String str : apartments) {
			if (builds.stream().filter(s->s.getId().equals(str.split(":")[0])).count()==0) {
				throw e;
			}
			if(Integer.valueOf(str.split(":")[1])<1 || Integer.valueOf(str.split(":")[1])>100) {
				throw e;
			}
			for (Owner o :owns) {
				for (Appartamento a : o.getAppartamenti()) {
					if (a.getB().equals(builds.stream().filter(s->s.getId().equals(str.split(":")[0])).collect(Collectors.toList()).get(0)) && a.getNum().equals(Integer.valueOf(str.split(":")[1]))) {
						throw e;

					}
				}
			}

		}
		owns.add(new Owner(owner, apartments));
		
	}

	/**
	 * Returns a map ( number of apartments => list of buildings ) 
	 * 
	 */
	public SortedMap<Integer, List<String>> getBuildings() {
		Map<Integer, List<String>> m = builds.stream().collect(Collectors.groupingBy(s->s.getNum(),Collectors.mapping(s->s.getId(), Collectors.toList())));
		
		return m.entrySet().stream().collect(TreeMap::new, (map,s)->map.put(s.getKey(),s.getValue().stream().sorted().collect(Collectors.toList())),TreeMap::putAll);
	}

	public class Professional {
		private String profession;
		public String getProfession() {
			return profession;
		}

		private String id;

		public String getId() {
			return id;
		}

		public Professional(String profession, String id) {
			this.id=id;
			this.profession=profession;
		}
	}
	List<Professional> profs = new ArrayList<>();
	public void addProfessionals(String profession, String... professionals) throws PropertyException {
		PropertyException e = new PropertyException();

		if(profs.stream().filter(s->s.getProfession().equals(profession)).count()>0) {
			throw e;
		}

		for (String id : professionals) {
			if(profs.stream().filter(s->s.getId().equals(id)).count()>0) {
				throw e;
			}
			profs.add(new Professional(profession, id));
		}
		



				
	}

	/**
	 * Returns a map ( profession => number of workers )
	 *
	 */
	public SortedMap<String, Integer> getProfessions() {
		Map<String, Long> m = profs.stream().collect(Collectors.groupingBy(s->s.getProfession(), Collectors.counting()));

		
		return m.entrySet().stream().collect(TreeMap::new, (map,s)->map.put(s.getKey(),s.getValue().intValue()),TreeMap::putAll);
	}

	public class Request {
		private Integer totAmount=0;
		public Integer getTotAmount() {
			return totAmount;
		}

		public void addTotAmount(Integer totAmount) {
			this.totAmount += totAmount;
		}

		private Professional prof = new Professional("ciao", "goat");
		public Professional getProf() {
			return prof;
		}

		public void setProf(Professional prof) {
			this.prof = prof;
		}

		private Integer id;
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		private String status;
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		private Owner own;
		public Owner getOwn() {
			return own;
		}

		private Appartamento ap;
		public Appartamento getAp() {
			return ap;
		}

		private String profession;

		public String getProfession() {
			return profession;
		}

		public Request(String owner, String app, String profession) {
			this.profession=profession;
			this.own=owns.stream().filter(s->s.getId().equals(owner)).collect(Collectors.toList()).get(0);
			this.ap=apps.stream().filter(s->s.getB().getId().equals(app.split(":")[0]) && s.getNum().equals(Integer.valueOf(app.split(":")[1]))).collect(Collectors.toList()).get(0);
			this.status="Pending";
		}
	}

	List<Request> reqs = new ArrayList<>();

	public int addRequest(String owner, String apartment, String profession) throws PropertyException {
		PropertyException e = new PropertyException();

		if(profs.stream().filter(s->s.getProfession().equals(profession)).count()==0) {
			throw e;
		}

		if(owns.stream().filter(s->s.getId().equals(owner)).count()==0) {
			throw e;
		}

		if(apps.stream().filter(s->s.getB().getId().equals(apartment.split(":")[0]) && s.getNum().equals(Integer.valueOf(apartment.split(":")[1]))).count()==0) {
			throw e;
		}

		if(!owns.stream().filter(s->s.getId().equals(owner)).collect(Collectors.toList()).get(0).getAppartamenti().contains(apps.stream().filter(s->s.getB().getId().equals(apartment.split(":")[0]) && s.getNum().equals(Integer.valueOf(apartment.split(":")[1]))).collect(Collectors.toList()).get(0))) {
			throw e;
		}

		Request r = new Request(owner, apartment, profession);
		reqs.add(r);
		r.setId(reqs.size());

		
		return r.getId();
	}

	public void assign(int requestN, String professional) throws PropertyException {
		PropertyException e = new PropertyException();
		Professional p = profs.stream().filter(s->s.getId().equals(professional)).collect(Collectors.toList()).get(0);

		if (requestN<1 || requestN>reqs.size()) {
			throw e;
		}
		if (!reqs.get(requestN-1).getProfession().equals(p.getProfession())) {
			throw e;
		}
		if (!reqs.get(requestN-1).getStatus().equals("Pending")) {
			throw e;
		}

		reqs.get(requestN-1).setProf(p);
		
		reqs.get(requestN-1).setStatus("Assigned");

		
		
		
	}

	public List<Integer> getAssignedRequests() {
		
		return reqs.stream().filter(s->s.getStatus().equals("Assigned")).map(s->s.getId()).sorted().collect(Collectors.toList());
	}

	
	public void charge(int requestN, int amount) throws PropertyException {
		PropertyException e = new PropertyException();
		if (requestN<1 || requestN>reqs.size()) {
			throw e;
		}
		if (!reqs.get(requestN-1).getStatus().equals("Assigned")) {
			throw e;
		}

		if (amount<0 || amount>1000) {
			throw e;
		}
		reqs.get(requestN-1).addTotAmount(amount);
		reqs.get(requestN-1).setStatus("Completed");

		
		
	}

	/**
	 * Returns the list of request ids
	 * 
	 */
	public List<Integer> getCompletedRequests() {
		
		return reqs.stream().filter(s->s.getStatus().equals("Completed")).map(s->s.getId()).sorted().collect(Collectors.toList());
	}
	
	/**
	 * Returns a map ( owner => total expenses )
	 * 
	 */
	public SortedMap<String, Integer> getCharges() {
		Map<String, Integer> m = reqs.stream().filter(s->s.getStatus().equals("Completed")).collect(Collectors.groupingBy(s->s.getOwn().getId(), Collectors.summingInt(s->s.getTotAmount())));

	
		return m.entrySet().stream().collect(TreeMap::new, (map,s)->map.put(s.getKey(),s.getValue()), TreeMap::putAll);
	}

	/**
	 * Returns the map ( building => ( profession => total expenses) ).
	 * Both buildings and professions are sorted alphabetically
	 * 
	 */
	public SortedMap<String, Map<String, Integer>> getChargesOfBuildings() {

		System.out.println(reqs.stream().map(s->s.getProf().getId()).collect(Collectors.toList()));

		Map<String, Map<String, Integer>> m = reqs.stream().collect(Collectors.groupingBy(s->s.getAp().getB().getId(), Collectors.groupingBy(k->k.getProf().getId(), Collectors.summingInt(k->k.getTotAmount()))));


		return m.entrySet().stream().collect(TreeMap::new, (map,s)->{	
			map.put(s.getKey(),new TreeMap<>());
			for (Map.Entry<String, Integer> entry :s.getValue().entrySet()) {
				if (entry.getValue()!=0) {
					map.get(s.getKey()).put(entry.getKey(), entry.getValue());
				}

			}
			if (map.get(s.getKey()).size()==0) {
				map.remove(s.getKey());
			}
			
			}, TreeMap::putAll);
	}

}
