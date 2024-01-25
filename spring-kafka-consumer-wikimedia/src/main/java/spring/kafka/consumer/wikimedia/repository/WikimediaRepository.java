package spring.kafka.consumer.wikimedia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.kafka.consumer.wikimedia.entity.WikimediaEntity;

/**
 * The {@code WikimediaRepository} interface extends {@link JpaRepository} and provides
 * data access methods for the {@link WikimediaEntity} entity. It allows for basic CRUD
 * operations on the associated database table.
 *
 * <p>This repository is used by the Spring Data JPA framework to interact with the underlying
 * database and perform operations such as saving, updating, deleting, and querying
 * {@link WikimediaEntity} objects.
 *
 * <p>Usage example:
 * <pre>
 *     // Inject the repository where needed
 *     {@literal @}Autowired
 *     private WikimediaRepository wikimediaRepository;
 *
 *     // Use repository methods
 *     List<WikimediaEntity> entities = wikimediaRepository.findAll();
 *     // Perform other CRUD operations...
 * </pre>
 *
 * @author Rajnesh Thakur
 * @version 1.0
 * @since 2024-01-25
 */
public interface WikimediaRepository extends JpaRepository<WikimediaEntity, Long> {
    // Additional custom query methods can be added here if needed.
}

