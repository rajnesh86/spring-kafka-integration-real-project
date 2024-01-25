package spring.kafka.producers.wikimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProducersApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducersApp.class);
    }

    @Autowired
    private WikimediaProducer wikimediaProducer;

    @Override
    public void run(String... args) throws Exception {
        wikimediaProducer.sendMessage();
    }
}
