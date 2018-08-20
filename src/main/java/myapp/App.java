package myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class App {

    @Autowired
    Client client;
    @Autowired
    @Qualifier("fileEventLogger")
    IEventLogger  eventLogger;

    public App(){

    }

     App(Client client, IEventLogger eventLogger){
        this.client=client;
        this.eventLogger=eventLogger;
    }

    public static void main(String[] args){

//        App app = new App();
//
//        app.client = new Client("1", "Bob Smith");
//        app.eventLogger = new ConsoleEventLogger();
//        app.logEvent("Some event for user 1");

//        //работает для xml конфигурации бинов
//        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//
//        App app = (App) ctx.getBean("app");
//
//        Event event1 = (Event) ctx.getBean("event");
//        app.logEvent(event1, "Some event for 1");
//
//        Event event2 = (Event) ctx.getBean("event");
//        app.logEvent(event2, "Some event for 2");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("myapp");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says:" + client.getGreeting());

        Event event1 = ctx.getBean(Event.class);
        app.logEvent(event1, "Some event for 1");

        Event event2 = ctx.getBean(Event.class);
        app.logEvent(event2, "Some event for 2");

        Event event3 = ctx.getBean(Event.class);
        app.logEvent(event3, "Some event for 3");

        /* for fun */
        System.out.println(App.class);

        Scanner myscanner = new Scanner(System.in);
        myscanner.nextInt();

        ctx.close();

    }


    private void logEvent(Event event , String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
        System.out.printf("%s @$!\n", this.client.getGreeting());
    }
}
