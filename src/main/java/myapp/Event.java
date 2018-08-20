package myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.text.DateFormat;
import java.util.Date;

@Component
@Scope("prototype")
public class Event {

    public int id =  (int) Math.round(Math.random()*100);
    private String msg;

    @Autowired
    @Qualifier("newDate")
    public Date date;
    @Autowired
    public DateFormat df;

    public Event(){

    }

    public Event(Date date, DateFormat df){
        this.date=date;
        this.df=df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public  String toString(){
        this.df.format(this.date);
        return String.format("id is %d; msg is %s date is %s", this.id, this.msg, this.date.toString());
    }
}
