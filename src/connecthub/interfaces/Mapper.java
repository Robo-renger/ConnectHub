package connecthub.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//UTILITY INTERFACE

public interface Mapper<T, ID> {

    // Get the database file for this entity
    void setDataBaseFile();
    
    // Retrieve a single entity using entity properties
    Optional<T> get(List<Predicate<User>> filters);

    // Method overloading for the generic get method to retrieve an entity only by passing its id
    Optional<T> get(int id);

    // Retrieve all entities
    List<T> getAll();

    // Create a new entity
    void create(T entity);

    // Delete an entity by its ID
    boolean delete(ID id);

    // Update an existing entity
    boolean update(int id, T entity);

    // Utility method for getting the TypeReference for Jackson
    TypeReference<List<T>> getTypeReference();
}
