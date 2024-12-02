package forum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Forum {
    private String url;
    
    public Forum(String url){
        this.url=url;

    }
    
    public String getUrl(){
        return this.url;
    }

    List<User> utenti = new ArrayList<>();

    public User registerUser(String nick, String first, String last, String email, String password)
        throws DuplicateNickname {
            if(utenti.stream().filter(s->s.getNick().equals(nick)).count()>0) {
                throw new DuplicateNickname();
            }
            User u = new User(nick,first,last,email,password);
            utenti.add(u);
        return u;
    }
    
    public User login(String nick, String password){
        if(utenti.stream().filter(s->s.getNick().equals(nick) && s.getPassword().equals(password)).count()==0) {
            return null;
        }
        return utenti.stream().filter(s->s.getNick().equals(nick) && s.getPassword().equals(password)).collect(Collectors.toList()).get(0);
    }

    List<Topic> args = new ArrayList<>();
    
    public Topic createTopic(String name, String subject, User creator){
        Topic t = new Topic(name, subject, creator);
        args.add(t);
        return t;
    }
    
    public Collection<Topic> listTopic(){
        return this.args;
    }
    
    public List<User> rankUsers(){
        return this.utenti.stream().sorted(Comparator.comparing((User s)->s.numSubmitted()).reversed()).collect(Collectors.toList());
    }
    
    public double averageMessages(){
        return args.stream().collect(Collectors.averagingDouble(s->s.numMessages()));
    }
    
}
