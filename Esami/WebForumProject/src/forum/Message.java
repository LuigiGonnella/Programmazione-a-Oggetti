package forum;

public class Message {
    private String title,body;
    private Topic arg;
    private User utente;

    public Message(String title, String body, Topic arg, User utente) {
        this.title=title;
        this.body=body;
        this.arg=arg;
        this.utente=utente;
    }

    public String getTitle(){
        return this.title;
    }
    
    public String getBody(){
        return this.body;
    }
    
    public User getUser(){
        return this.utente;
    }
    
    public Topic getTopic(){
        return this.arg;
    }
    
    public long getTimestamp(){
        return System.currentTimeMillis();
    }
}
