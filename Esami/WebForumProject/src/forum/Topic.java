package forum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Topic {

    private String name,subject;
    private User utente;

    public Topic(String name, String subject, User utente) {
        this.name=name;
        this.subject=subject;
        this.utente=utente;
    }

    public String getName(){
        return this.name;
    }

    public String getSubject(){
        return this.subject;
    }

    public User getUser(){
        return this.utente;
    }

    List<Message> mess = new ArrayList<>();
    public Message submitMessage(User user, String title, String body){
        Message m = new Message(title, body, this, user);
        user.addMess(m);
        mess.add(m);
        return m;
    }
    
    public Collection<Message> getMessagges(){
        return this.mess;
    }
    
    public long numMessages(){
        return this.mess.stream().count();
    }
}
