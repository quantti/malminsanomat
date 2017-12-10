package wad;

import java.time.ZoneId;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MSApplication {
    
//    @PostConstruct
//    public void started() {
//        ZoneId koti = ZoneId.of("Europe/Helsinki");
//        TimeZone.setDefault(TimeZone.getTimeZone(koti));
//    }

    public static void main(String[] args) {
        SpringApplication.run(MSApplication.class, args);
    }
}
