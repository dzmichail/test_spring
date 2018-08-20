package myapp;

import org.springframework.beans.factory.annotation.Value;

public class Client {

//    @Value("1")
    private String id;
//    @Value("John Smith")
    private String fullName;
    private String greeting;

//    public Client(String id, String fullName){
//        this.fullName=fullName;
//        this.id=id;
//    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName){
        this.fullName=fullName;
    }

    public String getGreeting(){ return this.greeting; }

    public void setGreeting(String gr) { this.greeting=gr; }

}
