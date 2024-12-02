package BankServices;

public class Withdrawal extends Operation{
	
	public Withdrawal(int d, double v) {
		super(d, v);
	}
	
	public String toString() {
		return date + "," + value + "-" ;
	}
}
