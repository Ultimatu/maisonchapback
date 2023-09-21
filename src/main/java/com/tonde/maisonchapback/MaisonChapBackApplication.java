package com.tonde.maisonchapback;

import com.tonde.maisonchapback.exceptions.CustomLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
public class MaisonChapBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaisonChapBackApplication.class, args);
        logApplicationStartup();
    }

    private static void logApplicationStartup() {
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            CustomLogger.log("ERROR", "The host name could not be determined, using `localhost` as fallback");
        }
        CustomLogger.log("INFO",
                "\n----------------------------------------------------------\n\t" +
                        "Application is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:8080/swagger-ui.html\n\t" +
                        "External: \thttp://"+hostAddress+":8080/swagger-ui.html\n\t"
        );
    }





}
