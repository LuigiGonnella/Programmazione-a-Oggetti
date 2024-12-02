package forum;

import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User> {
    private String nick,first,last,email,password;

    public User(String nick, String first, String last, String email, String password) {
        this.nick=nick;
        this.first=first;
        this.last=last;
        this.email=email;
        this.password=password;

    }

    
    public String getNick(){
        return this.nick;
    }

    public String getFirst(){
        return this.first;
    }

    public String getLast(){
        return this.last;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    List<Message> mess = new ArrayList<>();

    public List<Message> getMess() {
        return this.mess;
    }

    public void addMess(Message m) {
        this.mess.add(m);
    }
    
    public long numSubmitted(){
        return mess.stream().count();
    }

    public int compareTo(User arg0) {
        return 0;
    }
}
