package ua.kongross.lifefeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LifeFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeFeedApplication.class, args);
    }

}
