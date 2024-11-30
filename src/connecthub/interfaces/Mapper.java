package connecthub.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Optional;

public interface Mapper<T, ID> {

    // Get the database file for this entity
    String getDatabaseFile();

    // Retrieve a single entity by its ID
    Optional<T> get(ID id);

    // Retrieve all entities
    List<T> getAll();

    // Create a new entity
    void create(T entity);

    // Delete an entity by its ID
    boolean delete(ID id);

    // Update an existing entity
    boolean update(T entity);

    // Utility method for getting the TypeReference for Jackson
    TypeReference<List<T>> getTypeReference();
}
