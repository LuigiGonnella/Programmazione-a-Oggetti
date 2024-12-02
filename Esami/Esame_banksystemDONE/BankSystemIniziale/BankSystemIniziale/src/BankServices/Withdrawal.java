package BankServices;

public class Withdrawal extends Operation {	
    private String type;
	public String getType() {
		return type;
	}

	private Integer date;
	public Integer getDate() {
		return date;
	}

	private Double value;

	public Double getValue() {
		return value;
	}
    public Withdrawal(Integer date, String type, Double value) {
		this.type=type;
		this.date=date;
		this.value=value;
	}



    public String toString() {
        return this.getDate()+","+this.getValue()+"-";
    }
}