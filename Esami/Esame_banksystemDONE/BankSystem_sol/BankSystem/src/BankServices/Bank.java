package BankServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Bank {
	private String name;
	int codes;
	double totalDeposit;
	Map<Integer,Account> accounts;
	
	public Bank(String n) {
		name=n;
		codes=0;
		totalDeposit=0.;
		accounts = new TreeMap<>();
	}
	
	public String getName() {
		return name;
	}
	
	public int createAccount(String name, int date, double initial) {
		codes++;
		Account a = new Account(codes, name, date, initial);
		accounts.put(codes, a);
		totalDeposit += initial;
		return codes;
	}
	
	public Account deleteAccount(int code, int date) throws InvalidCode {
		Account a = accounts.get(code);
		if(a==null)
			throw new InvalidCode();
		double value = a.getBalance();
		try {
			a.addWithdraw(date, value);
		}
		catch(InvalidValue iv) {
		}
		accounts.remove(code);
		totalDeposit -= value;
		return a;
	}
	
	public Account getAccount(int code) throws InvalidCode {
		Account a = accounts.get(code);
		if(a==null)
			throw new InvalidCode();
		return a;
	}

	public void deposit(int code, int date, double value) throws InvalidCode {
		Account a = accounts.get(code);
		if(a==null)
			throw new InvalidCode();
		a.addDeposit(date, value);
		totalDeposit += value;
	}

	public void withdraw(int code, int date, double value) throws InvalidCode, InvalidValue {
		Account a = accounts.get(code);
		if(a==null)
			throw new InvalidCode();
		a.addWithdraw(date, value);
		totalDeposit -= value;
	}
	
	public void transfer(int fromCode, int toCode, int date, double value) throws InvalidCode, InvalidValue {
		Account from = accounts.get(fromCode);
		if(from==null)
			throw new InvalidCode();
		Account to = accounts.get(toCode);
		if(to==null)
			throw new InvalidCode();
		int d = from.addWithdraw(date, value);
		to.addDeposit(d, value);
	}
	
	public List<Account> getAccounts() {
		return new ArrayList<>(accounts.values());
	}
	
	public double getTotalDeposit() {
		return totalDeposit;
	}
	
	public List<Account> getAccountsByBalance(double low, double high) {
		List<Account> acs = getAccounts();
		return acs
				.stream()
				.filter(a -> a.getBalance() >= low && a.getBalance() <= high)
				.sorted((a1, a2) -> Double.compare(a2.getBalance(), a1.getBalance()))
				.collect(Collectors.toList());
	}
	
	public long getPerCentHigher(double min) {
		List<Account> acs = getAccounts();
		int s = acs.size();
		long h = acs
				.stream()
				.filter(a -> a.getBalance() >= min)
				.collect(Collectors.counting());
		return 100 * h/s;
	}
}
