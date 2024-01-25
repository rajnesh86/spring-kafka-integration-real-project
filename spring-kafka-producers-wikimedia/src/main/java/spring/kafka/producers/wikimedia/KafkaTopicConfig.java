package spring.kafka.producers.wikimedia;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * The {@code KafkaTopicConfig} class is a Spring configuration class that defines a Kafka topic
 * with the specified name using Spring Kafka's {@link TopicBuilder}.
 *
 * <p>This class is annotated with {@link Configuration} to indicate that it is a configuration class
 * and contains methods annotated with {@link Bean} for defining Spring beans.
 *
 * <p>Usage example:
 * <pre>
 *     // Inject the NewTopic bean where needed
 *     {@literal @}Autowired
 *     private NewTopic topic;
 *
 *     // The NewTopic bean can be used to create the Kafka topic
 * </pre>
 *
 * @author Rajnesh Thakur
 * @version 1.0
 * @since 2024-01-25
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * The name of the Kafka topic to be created.
     */
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    /**
     * Bean method that defines a {@link NewTopic} with the specified name using {@link TopicBuilder}.
     *
     * @return A {@link NewTopic} bean representing the Kafka topic.
     */
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName).build();
    }
}
