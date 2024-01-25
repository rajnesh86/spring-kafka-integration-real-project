package spring.kafka.consumer.wikimedia.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code WikimediaEntity} class represents an entity for storing Wikimedia data
 * in a database. This class is annotated with JPA annotations to map it to a database table.
 * It is designed to work with the Spring Kafka consumer application for processing Wikimedia events.
 *
 * <p>The entity is mapped to the "wikimedia_db" table, and it contains fields for storing
 * the ID and the Wikimedia event data in a CLOB (Character Large Object) format.
 *
 * <p>This class is also annotated with Lombok annotations for automatically generating
 * getter and setter methods.
 *
 * @author Rajnesh Thakur
 * @version 1.0
 * @since 2024-01-25
 */
@Entity
@Table(name = "wikimedia_db")
@Getter
@Setter
public class WikimediaEntity {

    /**
     * The unique identifier for the Wikimedia entity in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The field to store Wikimedia event data in a CLOB format.
     */
    @Lob
    private String wikiEventData;
}

