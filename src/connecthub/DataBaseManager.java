package connecthub;

import connecthub.interfaces.Identifiable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DataBaseManager {

    private static DataBaseManager instance;
    private String dbFile;
    private final ObjectMapper objectMapper;

    private DataBaseManager() {
        this.objectMapper = new ObjectMapper();
        initializeDatabase();
    }

    public static DataBaseManager getDBM() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    public void setDataBaseFile(String MapperDBFile) {
        System.out.println("db set to " + MapperDBFile);
        this.dbFile = MapperDBFile;
    }

    private void initializeDatabase() {
        System.out.println(dbFile);
        try {
            File file = new File(dbFile);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    objectMapper.writeValue(file, new ArrayList<>()); // Initialize with an empty list
                } catch (IOException e) {
                    System.out.println("Error initializing database: " + e.getMessage());
                }
            }
        } catch (NullPointerException e) {
//            System.out.println("");
        }

    }

    // Create: Add a new entity to the database, automatically generating and setting an ID
    public <T extends Identifiable> void createEntityWithID(T entity) throws IOException {
        List<T> entities = readEntities(new TypeReference<List<T>>() {
        });
        int latestId = getLatestID(entities);
        entity.setID(latestId + 1); // Increment ID by one
        entities.add(entity);
        writeEntities(entities);
    }

    // Retrieve the latest ID in the database
    public <T extends Identifiable> int getLatestID(List<T> entities) {
        return entities.stream()
                .map(Identifiable::getID)
                .max(Comparator.naturalOrder())
                .orElse(0); // Default to 0 if no entities exist
    }

    // Read: Retrieve all entities from the database
    public <T> List<T> readEntities(TypeReference<List<T>> typeReference) throws IOException {
        File file = new File(this.dbFile);
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, typeReference);
    }

    // Update: Update an existing entity in the database
    public <T> boolean updateEntity(TypeReference<List<T>> typeReference, T updatedEntity, java.util.function.Predicate<T> matchCriteria) throws IOException {
        List<T> entities = readEntities(typeReference);
        Optional<T> entityToUpdate = entities.stream().filter(matchCriteria).findFirst();

        if (entityToUpdate.isPresent()) {
            entities.remove(entityToUpdate.get());
            entities.add(updatedEntity);
            writeEntities(entities);
            return true;
        }
        return false;
    }

    // Delete: Remove an entity from the database
    public <T> boolean deleteEntity(TypeReference<List<T>> typeReference, java.util.function.Predicate<T> matchCriteria) throws IOException {
        List<T> entities = readEntities(typeReference);
        boolean removed = entities.removeIf(matchCriteria);

        if (removed) {
            writeEntities(entities);
        }
        return removed;
    }

    // Helper: Write entities to the database file
    private <T> void writeEntities(List<T> entities) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.dbFile), entities);
    }
}
