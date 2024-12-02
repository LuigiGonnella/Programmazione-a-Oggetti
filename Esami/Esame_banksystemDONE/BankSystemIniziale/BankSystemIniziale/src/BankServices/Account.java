package BankServices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account {
	public Integer code;
	public Integer getCode() {
		return code;
	}

	private Integer day;
	public Integer getDay() {
		return day;
	}

	private String name;
	public String getName() {
		return name;
	}

	private Double saldo;

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public Double getSaldo() {
		return saldo;
	}


	public Account(Integer day, String name, Double initial) {
		this.day=day;
		this.name=name;
		this.saldo=initial;
	}

	List<Operation> movements = new ArrayList<>();

	public void UpdateSaldo(String type, Double value) {
		Double n;
		if (type.equals("D")) {
			n=this.getSaldo()+value;
			this.setSaldo(n);
		}
		else if (type.equals("W")) {
			n=this.getSaldo()-value;
			this.setSaldo(n);
		}

	}

	public String toString() {
		
		return this.code+","+this.getName()+","+this.movements.get(movements.size()-1).getDate()+","+this.getSaldo();
	}
		
	public List<Operation> getMovements() {
		return movements.stream().sorted(Comparator.comparing(Operation::getDate).reversed()).toList();
	}
	
	public List<Deposit> getDeposits() {
		
		return movements.stream().filter(s->s.getType().equals("D")).map(s->(Deposit)s).sorted(Comparator.comparing(Deposit::getValue).reversed()).toList();
	}

	public List<Withdrawal> getWithdrawals() {
		return movements.stream().filter(s->s.getType().equals("W")).map(s->(Withdrawal)s).sorted(Comparator.comparing(Withdrawal::getValue).reversed()).toList();
	}
}
