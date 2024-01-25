package spring.kafka.consumer.wikimedia;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import spring.kafka.consumer.wikimedia.entity.WikimediaEntity;
import spring.kafka.consumer.wikimedia.repository.WikimediaRepository;

/**
 * The {@code WikimediaConsumer} class is a Spring service responsible for consuming
 * Wikimedia events from a Kafka topic and persisting them to a database using the
 * {@link WikimediaRepository}.
 *
 * <p>This class is annotated with {@link Service} to indicate that it is a Spring-managed
 * service bean.
 *
 * <p>Usage example:
 * <pre>
 *     // Inject the consumer where needed
 *     {@literal @}Autowired
 *     private WikimediaConsumer wikimediaConsumer;
 *
 *     // The consumer will automatically listen for messages when the application is running
 * </pre>
 *
 * @author Rajnesh Thakur
 * @version 1.0
 * @since 2024-01-25
 */
@Service
public class WikimediaConsumer {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WikimediaConsumer.class);

    /**
     * The repository for persisting Wikimedia entities to the database.
     */
    @Autowired
    private WikimediaRepository repository;

    /**
     * Constructor for initializing the consumer with the repository.
     *
     * @param repository The {@link WikimediaRepository} used for database operations.
     */
    public WikimediaConsumer(WikimediaRepository repository) {
        this.repository = repository;
    }

    /**
     * Kafka listener method that is triggered when a message is received on the configured topic.
     * It processes the received event message, logs it, and persists it to the database.
     *
     * @param eventMessage The received event message from the Kafka topic.
     */
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String eventMessage) {
        LOGGER.info(String.format("Event Message received: %s", eventMessage));

        WikimediaEntity entity = new WikimediaEntity();
        entity.setWikiEventData(eventMessage);

        repository.save(entity);
    }
}
