package sampleservice;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableEncryptableProperties
@EnableCaching
@Log4j2
public class SampleServiceApplication {

    @Autowired
    public SampleServiceApplication() {
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        SpringApplication app = new SpringApplication(SampleServiceApplication.class);
        app.run(args);
    }
}
