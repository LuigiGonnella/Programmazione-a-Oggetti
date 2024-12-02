package delivery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Delivery {
	
    public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
    
    
    /**
     * Creates a new customer entry and returns the corresponding unique ID.
     * 
     * The ID for the first customer must be 1.
     * 
     * 
     * @param name name of the customer
     * @param address customer address
     * @param phone customer phone number
     * @param email customer email
     * @return unique customer ID (positive integer)
     */

    List<Customer> customers = new ArrayList<>();

    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
        if (customers.stream().filter(s->s.getEmail().equals(email)).count()>0) {
            throw new DeliveryException();
        }
        Customer cust = new Customer(name, address, phone, email);
        cust.setNumProgr(customers.size()+1);

        customers.add(cust);

    	return cust.getNumProgr();
    }
    
    /**
     * Returns a string description of the customer in the form:
     * <pre>
     * "NAME, ADDRESS, PHONE, EMAIL"
     * </pre>
     * 
     * @param customerId
     * @return customer description string
     */
    public String customerInfo(int customerId){
       return customers.stream().filter(s->s.getNumProgr()==customerId).collect(Collectors.toList()).get(0).toString();
    }
    
    /**
     * Returns the list of customers, sorted by name
     * 
     */
    public List<String> listCustomers(){
    	return this.customers.stream().sorted(Comparator.comparing(s->s.getName())).map(s->s.toString()).collect(Collectors.toList());
    }
    
    /**
     * Add a new item to the delivery service menu
     * 
     * @param description description of the item (e.g. "Pizza Margherita", "Bunet")
     * @param price price of the item (e.g. 5 EUR)
     * @param category category of the item (e.g. "Main dish", "Dessert")
     * @param prepTime estimate preparation time in minutes
     */
    List<Menu> menus = new ArrayList<>();
    public void newMenuItem(String description, double price, String category, int prepTime){
        menus.add(new Menu(description, price, category, prepTime));
    	
    }
    
    /**
     * Search for items matching the search string.
     * The items are sorted by category first and then by description.
     * 
     * The format of the items is:
     * <pre>
     * "[CATEGORY] DESCRIPTION : PRICE"
     * </pre>
     * 
     * @param search search string
     * @return list of matching items
     */
    public List<String> findItem(String search){
     return this.menus.stream().filter(s->s.getDescription().toLowerCase().contains(search.toLowerCase())).sorted(Comparator.comparing((Menu s)->s.getCategory()).thenComparing(s->s.getDescription())).map(s->s.toString()).collect(Collectors.toList());
    }
    
    /**
     * Creates a new order for the given customer.
     * Returns the unique id of the order, a progressive
     * integer number starting at 1.
     * 
     * @param customerId
     * @return order id
     */

    List<Order> ordini = new ArrayList<>();
    public int newOrder(int customerId){
        int n = ordini.size()+1;
        ordini.add(new Order(customers.stream().filter(s->s.getNumProgr()==customerId).toList().get(0), n));
    	return n;
    }
    
    /**
     * Add a new item to the order with the given quantity.
     * 
     * If the same item is already present in the order is adds the
     * given quantity to the current quantity.
     * 
     * The method returns the final total quantity of the item in the order. 
     * 
     * The item is found through the search string as in {@link #findItem}.
     * If none or more than one item is matched, then an exception is thrown.
     * 
     * @param orderId the id of the order
     * @param search the item search string
     * @param qty the quantity of the item to be added
     * @return the final quantity of the item in the order
     * @throws DeliveryException in case of non unique match or invalid order ID
     */
    
    
   
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
        if (findItem(search).size()>1 || ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
        Menu m = menus.stream().filter(s->s.toString().equals(findItem(search).get(0))).collect(Collectors.toList()).get(0);
        

    	return ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).addItem(m, qty);
    }
    
    /**
     * Show the items of the order using the following format
     * <pre>
     * "DESCRIPTION, QUANTITY"
     * </pre>
     * 
     * @param orderId the order ID
     * @return the list of items in the order
     * @throws DeliveryException when the order ID in invalid
     */
    public List<String> showOrder(int orderId) throws DeliveryException {
        if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }


    	return this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getItems().stream().map(s->s.getMenu().getDescription()+", "+s.getQty()).collect(Collectors.toList());
    }
    
    /**
     * Retrieves the total amount of the order.
     * 
     * @param orderId the order ID
     * @return the total amount of the order
     * @throws DeliveryException when the order ID in invalid
     */
    public double totalOrder(int orderId) throws DeliveryException {
        if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
    	return this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getItems().stream().collect(Collectors.summingDouble(s->s.getQty()*s.getMenu().getPrice()));
    }
    
    /**
     * Retrieves the status of an order
     * 
     * @param orderId the order ID
     * @return the current status of the order
     * @throws DeliveryException when the id is invalid
     */
    public OrderStatus getStatus(int orderId) throws DeliveryException {
        if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
    	return this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getStatus();
    }
    
    /**
     * Confirm the order. The status goes from {@code NEW} to {@code CONFIRMED}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>start-up delay (conventionally 5 min)
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code NEW}
     */
    public int confirm(int orderId) throws DeliveryException {
    	if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
        if(this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getStatus()!=OrderStatus.NEW) {
            throw new DeliveryException();


        }

        this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).setStatus(OrderStatus.CONFIRMED);

        Integer maxprep = this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getMaxTime();

    	return  maxprep+20;
    }

    /**
     * Start the order preparation. The status goes from {@code CONFIRMED} to {@code PREPARATION}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code CONFIRMED}
     */
    public int start(int orderId) throws DeliveryException {
        if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
        if(this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getStatus()!=OrderStatus.CONFIRMED) {
            throw new DeliveryException();


        }

        this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).setStatus(OrderStatus.PREPARATION);

    	return this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getMaxTime()+15;
    }

    /**
     * Begins the order delivery. The status goes from {@code PREPARATION} to {@code ON_DELIVERY}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code PREPARATION}
     */
    public int deliver(int orderId) throws DeliveryException {
        if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
        if(this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getStatus()!=OrderStatus.PREPARATION) {
            throw new DeliveryException();


        }

        this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).setStatus(OrderStatus.ON_DELIVERY);
        
    	return 15;
    }
    
    /**
     * Complete the delivery. The status goes from {@code ON_DELIVERY} to {@code DELIVERED}
     * 
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code ON_DELIVERY}
     */
    public void complete(int orderId) throws DeliveryException {
        if(ordini.stream().filter(s->s.getCodO()==orderId).count()==0) {
            throw new DeliveryException();
        }
        if(this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).getStatus()!=OrderStatus.ON_DELIVERY) {
            throw new DeliveryException();


        }

        this.ordini.stream().filter(s->s.getCodO()==orderId).collect(Collectors.toList()).get(0).setStatus(OrderStatus.DELIVERED);
    	
    }
    
    /**
     * Retrieves the total amount for all orders of a customer.
     * 
     * @param customerId the customer ID
     * @return total amount
     */
    public double totalCustomer(int customerId){
    	return this.ordini.stream().filter(s->s.getCustomer().getNumProgr()==customerId && s.getStatus().equals(OrderStatus.DELIVERED)).collect(Collectors.summingDouble(s->{try {
            return totalOrder(s.getCodO());
            
        } catch (Exception e) {
            return 0.0;
        }
            } ));
    }
    
    /**
     * Computes the best customers by total amount of orders.
     *  
     * @return the classification
     */
    public SortedMap<Double,List<String>> bestCustomers(){
        Map<Double,List<String>> m = this.customers.stream().collect(Collectors.groupingBy(s->totalCustomer(s.getNumProgr()), Collectors.mapping(s->s.toString(), Collectors.toList())));

        SortedMap<Double,List<String>> fin = new TreeMap<>(Comparator.comparing(Double::doubleValue).reversed());

        for (Map.Entry<Double,List<String>> entry : m.entrySet()) {
            fin.put(entry.getKey(), entry.getValue());
        }





			return fin;					                	
        		
    }
    
// NOT REQUIRED
//
//    /**
//     * Computes the best items by total amount of orders.
//     *  
//     * @return the classification
//     */
//    public List<String> bestItems(){
//        return null;
//    }
//    
//    /**
//     * Computes the most popular items by total quantity ordered.
//     *  
//     * @return the classification
//     */
//    public List<String> popularItems(){
//        return null;
//    }

}
