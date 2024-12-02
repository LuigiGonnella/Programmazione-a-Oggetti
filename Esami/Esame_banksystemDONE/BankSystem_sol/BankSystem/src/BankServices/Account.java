package BankServices;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {
	int code;
	private String customer;
	private double balance;
	private int currentDate;
	private List<Deposit> deposits;
	private List<Withdrawal> withdrawals;
	
	public Account(int c, String name, int date, double initial) {
		code=c;
		customer=name;
		balance=initial;
		currentDate=date;
		deposits = new ArrayList<>();
		withdrawals = new ArrayList<>();
		Deposit d = new Deposit(date, initial);
		deposits.add(d);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		return code + "," + customer + "," + currentDate + "," + balance;
	}
	
	public int addDeposit(int date, double value) {		
		if(date >= currentDate)
			currentDate = date;
		deposits.add(new Deposit(currentDate, value));
		balance += value;
		return currentDate;
	}

	public int addWithdraw(int date, double value) throws InvalidValue {
		if(balance<value)
			throw new InvalidValue();
		if(date >= currentDate)
			currentDate = date;
		withdrawals.add(new Withdrawal(currentDate, value));
		balance -= value;
		return currentDate;
	}
		
	public List<Operation> getMovements() {
		List<Operation> m = new ArrayList<>(deposits);
		m.addAll(withdrawals);
		return m
				.stream()
				.sorted((m1,m2) -> m2.getDate()-m1.getDate())
				.collect(Collectors.toList());
	}
	
	public List<Deposit> getDeposits() {
		return deposits
				.stream()
				.sorted((d1,d2) -> Double.compare(d2.getValue(), d1.getValue()))
				.collect(Collectors.toList());
	}

	public List<Withdrawal> getWithdrawals() {
		return withdrawals
				.stream()
				.sorted((w1,w2) -> Double.compare(w2.getValue(), w1.getValue()))
				.collect(Collectors.toList());
	}
}
