package trail;

public class Runner {
    private Integer BidNumber;
   


    public void setBidNumber(Integer bidNumber) {
        BidNumber = bidNumber;
    }

    private String name, surname;

    public Runner(String name, String surname) {
        this.name=name;
        this.surname=surname;
    }

    
    public int getBibNumber(){
        return this.BidNumber;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

}
