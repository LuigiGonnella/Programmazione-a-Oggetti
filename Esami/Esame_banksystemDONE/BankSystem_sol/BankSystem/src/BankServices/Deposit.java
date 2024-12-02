package BankServices;

public class Deposit extends Operation{
	
	public Deposit(int d, double v) {
		super(d, v);
	}
	
	public String toString() {
		return date + "," + value + "+" ;
	}
}
