package spring.kafka.producers.wikimedia;

import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * The {@code WikimediaProducer} class is responsible for producing Wikimedia events by
 * connecting to the Wikimedia recent change stream and sending messages to a Kafka topic.
 *
 * <p>This class is annotated with {@link Service} to indicate that it is a Spring-managed
 * service bean.
 *
 * <p>Usage example:
 * <pre>
 *     // Inject the producer where needed
 *     {@literal @}Autowired
 *     private WikimediaProducer wikimediaProducer;
 *
 *     // Call the sendMessage method to start producing messages
 *     wikimediaProducer.sendMessage();
 * </pre>
 *
 * @author Rajnesh Thakur
 * @version 1.0
 * @since 2024-01-25
 */
@Service
public class WikimediaProducer {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WikimediaProducer.class);

    /**
     * The name of the Kafka topic to which Wikimedia events will be sent.
     */
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    /**
     * The KafkaTemplate for sending messages to Kafka.
     */
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Constructor for initializing the producer with a KafkaTemplate.
     *
     * @param kafkaTemplate The {@link KafkaTemplate} used for sending messages to Kafka.
     */
    public WikimediaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Method to start producing Wikimedia events by connecting to the Wikimedia recent change stream
     * and sending messages to the configured Kafka topic.
     *
     * @throws InterruptedException If the thread is interrupted during sleep.
     */
    public void sendMessage() throws InterruptedException {
        // Initialize event handler and event source
        BackgroundEventHandler eventHandler = new WikemediaEventHandler(kafkaTemplate, topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        BackgroundEventSource eventSource = new BackgroundEventSource.Builder(
                                            eventHandler,
                                            new EventSource.Builder(ConnectStrategy.http(URI.create(url))))
                                            .build();

        // Start the event source and wait for a specified duration (e.g., 10 minutes)
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
