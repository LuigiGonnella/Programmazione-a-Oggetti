package BankServices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {
	private String name;
	
	public Bank(String n) {
		this.name=n;
	}
	
	public String getName() {
		return this.name;
	}

	List<Account> accs = new ArrayList<>();
	
	public int createAccount(String name, int date, double initial) {
		Account a = new Account(date, name, initial
		);
		a.movements.add(new Deposit(date, "D", initial));
		a.code=accs.size()+1;
		accs.add(a);
		return accs.size(); //per indice fai -1
	}
	
	public Account deleteAccount(int code, int date) throws InvalidCode {
		InvalidCode e = new InvalidCode();
		int data;
		if (code>accs.size()) {
			throw e;
		}
		if (date<accs.get(code-1).movements.get(accs.get(code-1).movements.size()-1).getDate()) {
			data=accs.get(code-1).movements.get(accs.get(code-1).movements.size()-1).getDate();
		}
		else {
			data=date;
		}

		try {
			withdraw(code, data, accs.get(code-1).getSaldo());
		} catch (InvalidValue e1) {
			return null;
		}
		Account a =	accs.get(code-1);
		List<Account> newaccs = accs.stream().filter(s->!(s.getCode().equals(a.getCode()))).toList();
		accs=newaccs;
		return a;
	}
	
	public Account getAccount(int code) throws InvalidCode {
		InvalidCode e = new InvalidCode();
		if (code>accs.size()) {
			throw e;
		}
		return accs.get(code-1);
	}

	public void deposit(int code, int date, double value) throws InvalidCode {
		InvalidCode e = new InvalidCode();
		if (code>accs.size()) {
			throw e;
		}
		if (date<accs.get(code-1).movements.get(accs.get(code-1).movements.size()-1).getDate()) {
			accs.get(code-1).movements.add(new Deposit(accs.get(code-1).movements.get(accs.get(code-1).movements.size()-1).getDate(),"D" ,value));

		}
		else {
			accs.get(code-1).movements.add(new Deposit(date,"D" ,value));

		}
		accs.get(code-1).UpdateSaldo("D", value);

	}

	public void withdraw(int code, int date, double value) throws InvalidCode, InvalidValue {
		InvalidCode e = new InvalidCode();
		InvalidValue e1 = new InvalidValue();
		if (code>accs.size()) {
			throw e;
		}
		if (value>accs.get(code-1).getSaldo()) {
			throw e1;
		}
		if (date<accs.get(code-1).movements.get(accs.get(code-1).movements.size()-1).getDate()) {
			accs.get(code-1).movements.add(new Withdrawal(accs.get(code-1).movements.get(accs.get(code-1).movements.size()-1).getDate(),"W" ,value));

		}
		else {
			accs.get(code-1).movements.add(new Withdrawal(date,"W" ,value));

		}
		accs.get(code-1).UpdateSaldo("W", value);
	}
	
	public void transfer(int fromCode, int toCode, int date, double value) throws InvalidCode, InvalidValue {
		InvalidCode e = new InvalidCode();
		InvalidValue e1 = new InvalidValue();
		if (fromCode>accs.size() || toCode>accs.size()) {
			throw e;
		}
		if (value>accs.get(fromCode-1).getSaldo()) {
			throw e1;
		}
		int dataBene, dataOrd;
		if (date<accs.get(toCode-1).movements.get(accs.get(toCode-1).movements.size()-1).getDate()) {
			dataBene=accs.get(toCode-1).movements.get(accs.get(toCode-1).movements.size()-1).getDate();
		}
		else {
			dataBene=date;
		}

		if (date<accs.get(fromCode-1).movements.get(accs.get(fromCode-1).movements.size()-1).getDate()) {
			dataOrd=accs.get(fromCode-1).movements.get(accs.get(fromCode-1).movements.size()-1).getDate();
		}
		else {
			dataOrd=date;
		}

		if(dataBene>=dataOrd) {
			deposit(toCode, dataBene, value);
			withdraw(fromCode, dataOrd, value);
		}

	}
	
	public double getTotalDeposit() {
		return accs.stream().collect(Collectors.summingDouble(s->s.getSaldo()));
	}
	
	public List<Account> getAccounts() {
		return accs.stream().sorted(Comparator.comparing(s->s.getCode())).toList();
	}
	
	public List<Account> getAccountsByBalance(double low, double high) {
		return accs.stream().filter(s->{if(s.getSaldo()>=low && s.getSaldo()<=high) {
			return true;
		} else {
			return false;
		}}).sorted(Comparator.comparing((Account s)->s.getSaldo()).reversed()).toList();
	}
	
	public long getPerCentHigher(double min) {
		Integer n = accs.size();
		return accs.stream().filter(s->s.getSaldo()>=min).count()*100/n;
	}
}
