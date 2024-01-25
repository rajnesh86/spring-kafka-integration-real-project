package spring.kafka.producers.wikimedia;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * The {@code WikemediaEventHandler} class implements the {@link BackgroundEventHandler} interface
 * to handle events received from the Wikimedia recent change stream. It logs the event data and
 * sends it to a Kafka topic using the provided {@link KafkaTemplate}.
 *
 * <p>Usage example:
 * <pre>
 *     // Create an instance of the event handler
 *     WikemediaEventHandler eventHandler = new WikemediaEventHandler(kafkaTemplate, topicName);
 *
 *     // Use the event handler in the BackgroundEventSource.Builder
 *     BackgroundEventSource eventSource = new BackgroundEventSource.Builder(eventHandler, ...).build();
 * </pre>
 *
 * @author Rajnesh Thakur
 * @version 1.0
 * @since 2024-01-25
 */
public class WikemediaEventHandler implements BackgroundEventHandler {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WikemediaEventHandler.class);

    /**
     * The KafkaTemplate for sending messages to Kafka.
     */
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * The name of the Kafka topic to which Wikimedia events will be sent.
     */
    private String topic;

    /**
     * Constructor for initializing the event handler with a KafkaTemplate and topic name.
     *
     * @param kafkaTemplate The {@link KafkaTemplate} used for sending messages to Kafka.
     * @param topic         The name of the Kafka topic to which events will be sent.
     */
    public WikemediaEventHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        // Method to be implemented if needed when the connection is opened.
    }

    @Override
    public void onClosed() throws Exception {
        // Method to be implemented if needed when the connection is closed.
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("Event Data -> %s", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {
        // Method to be implemented if needed when a comment is received.
    }

    @Override
    public void onError(Throwable throwable) {
        // Method to be implemented if needed to handle errors.
    }
}
