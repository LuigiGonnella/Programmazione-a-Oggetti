package restaurantChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chain {	
		Map<String,Restaurant> restaurants;
		
		public Chain(){
			restaurants = new HashMap<>();
		}
		public void addRestaurant(String name, int tables) throws InvalidName{
			Restaurant r = restaurants.get(name);
			if(r!=null) throw new InvalidName();
			r = new Restaurant(name, tables);
			restaurants.put(name,r);
		}
		public Restaurant getRestaurant(String name) throws InvalidName{
			Restaurant r = restaurants.get(name);
			if(r==null) throw new InvalidName();
			return r;
		}
		public List<Restaurant> sortByIncome(){
			return restaurants.values()
					.stream()
					.sorted((r1,r2) -> (int)(r2.getIncome()-r1.getIncome()))
					.collect(Collectors.toList());
		}
		public List<Restaurant> sortByRefused(){
			return restaurants.values()
					.stream()
					.sorted((r1,r2) ->(r1.getRefused()-r2.getRefused()))
					.collect(Collectors.toList());
		}
		public List<Restaurant> sortByUnusedTables(){
			return restaurants.values()
					.stream()
					.sorted((r1,r2) ->(r1.getUnusedTables()-r2.getUnusedTables()))
					.collect(Collectors.toList());
		}
}
