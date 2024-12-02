package delivery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import delivery.Delivery.OrderStatus;


public class Order {
	private Customer customer;
	private int codO;
	private OrderStatus status;
	
	public Order(Customer customer, int codO) {
		this.status=OrderStatus.NEW;
		this.customer = customer;
		this.codO = codO;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getCodO() {
		return codO;
	}
	public void setCodO(int codO) {
		this.codO = codO;
	}

	public class Item {
		private Menu menu;
		public Menu getMenu() {
			return menu;
		}

		private int qty;

		public void setQty(int qty) {
			this.qty = qty;
		}

		public int getQty() {
			return qty;
		}

		public Item(Menu menu, int qty) {
			this.menu=menu;
			this.qty=qty;
		}
	}

	List<Item> items = new ArrayList<>();
	public int addItem(Menu menu, int qty) {
		Item i = new Item(menu, qty);

		if (items.stream().filter(s->s.getMenu().equals(menu)).count()>0) {
			Integer tot = items.stream().filter(s->s.getMenu().equals(menu)).collect(Collectors.toList()).get(0).getQty();

			items = items.stream().filter(s->!s.getMenu().equals(menu)).collect(Collectors.toList());

			i.setQty(tot+qty);
			items.add(i);
			return tot+qty;

			

		}
		items.add(i);
		return qty;
	
	}
	
	public List<Item> getItems(){
		
		return this.items;
		
	}
	
	
	public void setStatus(OrderStatus new1) {
		this.status=new1;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public int getMaxTime() {
		return this.items.stream().map(s->s.getMenu().getPrepTime()).sorted(Comparator.comparing((Integer s)->s).reversed()).collect(Collectors.toList()).get(0);
	}
	
	

}
