package BankServices;

public abstract class Operation {
	public abstract String getType();

	public abstract Integer getDate();

	public abstract Double getValue();

	public abstract String toString();
}
