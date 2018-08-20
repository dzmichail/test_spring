package myapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileEventLogger implements IEventLogger {

    @Value("logfile.txt")
    public String filename;
    private File file;

    public FileEventLogger(String filename){
        this.filename=filename;
    }


    public FileEventLogger(){
        this.filename=filename;
    }

    public void logEvent(Event event) {
        try(FileWriter logfile =  new FileWriter(filename, true)){
            logfile.write(event.toString() +"\n" );
        }catch (IOException ex){
            //System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException{
        file = new File(this.filename);
        if (! file.canWrite()){
            throw new IOException("Can't write into file!!!");
        }
    }

}
