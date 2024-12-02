package trail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Trail {

    List<Runner> runners = new ArrayList<>();

    public int newRunner(String name, String surname){
        Runner r = new Runner(name, surname);
        runners.add(r);
        r.setBidNumber(runners.size());
        return r.getBibNumber();
    }
    
    public Runner getRunner(int bibNumber){
        return runners.get(bibNumber-1);
    }
    
    public Collection<Runner> getRunner(String surname){
        return this.runners.stream().filter(s->s.getSurname().equals(surname)).sorted(Comparator.comparing(s->s.getBibNumber())).collect(Collectors.toList());
    }
    
    public List<Runner> getRunners(){
        return this.runners.stream().sorted(Comparator.comparing(s->s.getBibNumber())).collect(Collectors.toList());
    }

    public List<Runner> getRunnersByName(){
        return this.runners.stream().sorted(Comparator.comparing((Runner s)->s.getSurname()).thenComparing(s->s.getName()).thenComparing(s->s.getBibNumber())).collect(Collectors.toList());
    }
    
    List<Location> percorso = new ArrayList<>();
    public void addLocation(String location){
        Location r = new Location(location);
        percorso.add(r);
        r.setOrderNum(percorso.size()-1);
        
        
    }
//    public void addLocation(String name, double lat, double lon, double elevation){
//        
//    }

    public Location getLocation(String location){
        return this.percorso.stream().filter(s->s.getName().equals(location)).collect(Collectors.toList()).get(0);
    }

    public List<Location> getPath(){
        return this.percorso;
    }
    
    public class Delegato {
        private String name, surname, id;
        private List<Location> assigned = new ArrayList<>();

        public List<Location> getAssigned() {
            return assigned;
        }

        public void setAssigned(List<Location> assigned) {
            this.assigned = assigned;
        }

        public String getId() {
            return id;
        }

        public String getSurname() {
            return surname;
        }

        public String getName() {
            return name;
        }

        public Delegato(String name, String surname, String id) {
            this.id=id;
            this.name=name;
            this.surname=surname;
        }
    }

    List<Delegato> delegs = new ArrayList<>();
    public void newDelegate(String name, String surname, String id){
        delegs.add(new Delegato(name, surname, id));
        
    }

    public List<String> getDelegates(){
        return this.delegs.stream().map(s->s.getSurname()+", "+s.getName()+", "+s.getId()).sorted().collect(Collectors.toList());
    }
    

    public void assignDelegate(String location, String delegate) throws TrailException {
        TrailException e = new TrailException();
        if (this.percorso.stream().filter(s->s.getName().equals(location)).count()==0) {
            throw e;
        }

        if (this.delegs.stream().filter(s->s.getId().equals(delegate)).count()==0) {
            throw e;
        }


        Location l = this.percorso.stream().filter(s->s.getName().equals(location)).collect(Collectors.toList()).get(0);

        Delegato d = this.delegs.stream().filter(s->s.getId().equals(delegate)).collect(Collectors.toList()).get(0);

        l.getDeles().add(d);
        d.getAssigned().add(l);
       
    }
 
    public List<String> getDelegates(String location){
        Location l = this.percorso.stream().filter(s->s.getName().equals(location)).collect(Collectors.toList()).get(0);

        return l.getDeles().stream().map(s->s.getSurname()+", "+s.getName()+", "+s.getId()).sorted().collect(Collectors.toList());
    }
    public class Passage{
        private Delegato d;
        public Delegato getD() {
            return d;
        }

        private Location l;
        public Location getL() {
            return l;
        }

        private Runner r;
        public Runner getR() {
            return r;
        }

        private Long t;

        public Long getT() {
            return t;
        }

        public Passage(Delegato d, Location l, Runner r, Long t) {
            this.d=d;
            this.l=l;
            this.r=r;
            this.t=t;
            
        }
    }

    List<Passage> pass = new ArrayList<>();
    
    public long recordPassage(String delegate, String location, int bibNumber) throws TrailException {
        TrailException e = new TrailException();
        if (this.delegs.stream().filter(s->s.getId().equals(delegate)).count()==0) {
            throw e;
        }
        if (this.percorso.stream().filter(s->s.getName().equals(location)).count()==0) {
            throw e;
        }
        if (this.runners.stream().filter(s->s.getBibNumber()==bibNumber).count()==0) {
            throw e;
        }
        Delegato d = this.delegs.stream().filter(s->s.getId().equals(delegate)).collect(Collectors.toList()).get(0);

        Location l = this.percorso.stream().filter(s->s.getName().equals(location)).collect(Collectors.toList()).get(0);

        Runner r = this.runners.stream().filter(s->s.getBibNumber()==bibNumber).collect(Collectors.toList()).get(0);

        Long t = System.currentTimeMillis();

        pass.add(new Passage(d, l, r, t));

        return t;
    }
    
    public long getPassTime(String position, int bibNumber) throws TrailException {
        TrailException e = new TrailException();

        if (this.percorso.stream().filter(s->s.getName().equals(position)).count()==0) {
            throw e;
        }
        if (this.runners.stream().filter(s->s.getBibNumber()==bibNumber).count()==0) {
            throw e;
        }

        if (this.pass.stream().filter(s->s.getL().getName().equals(position) && s.getR().getBibNumber()==bibNumber).count()==0) {
            return -1;
        }
        return this.pass.stream().filter(s->s.getL().getName().equals(position) && s.getR().getBibNumber()==bibNumber).collect(Collectors.toList()).get(0).getT();
    }
    
    public List<Runner> getRanking(String location){

        return this.pass.stream().filter(s->s.getL().getName().equals(location)).sorted(Comparator.comparing(s->s.getT())).map(s->s.getR()).collect(Collectors.toList());
    }

    public List<Runner> getRanking(){
        return this.pass.stream().sorted(Comparator.comparing((Passage s)->s.getL().getOrderNum()).reversed().thenComparing(s->s.getT())).map(s->s.getR()).distinct().collect(Collectors.toList());
    }
}
