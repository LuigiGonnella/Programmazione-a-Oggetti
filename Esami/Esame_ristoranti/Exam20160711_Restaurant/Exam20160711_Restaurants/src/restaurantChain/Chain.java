package restaurantChain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.InvalidNameException;

public class Chain {	
		List<Restaurant> ristos = new ArrayList<>();

		public void addRestaurant(String name, int tables) throws InvalidName{
			if (ristos.stream().filter(s->s.getName().equals(name)).count()>0) {
				throw new InvalidName();
			}
			ristos.add(new Restaurant(name, tables));
			
		}
		
		public Restaurant getRestaurant(String name) throws InvalidName{
			if (ristos.stream().filter(s->s.getName().equals(name)).count()==0) {
				throw new InvalidName();
			}

			return ristos.stream().filter(s->s.getName().equals(name)).collect(Collectors.toList()).get(0);
		}
		
		public List<Restaurant> sortByIncome(){
			return this.ristos.stream().sorted(Comparator.comparing((Restaurant s)->s.getIncome()).reversed()).collect(Collectors.toList());
		}
		
		public List<Restaurant> sortByRefused(){
			return this.ristos.stream().sorted(Comparator.comparing((Restaurant s)->s.getNonaccolte())).collect(Collectors.toList());
		}
		
		public List<Restaurant> sortByUnusedTables(){
			return this.ristos.stream().sorted(Comparator.comparing((Restaurant s)->s.getDisponibili())).collect(Collectors.toList());
		}
}
