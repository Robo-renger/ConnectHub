package connecthub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import connecthub.entities.User;

import java.io.*;
import java.util.*;

public class FileHandler {

    private String fileName;
    private String mode;
    private ObjectMapper objectMapper;

    public FileHandler(String fileName, String mode) {
        this.fileName = fileName;
        this.mode = mode;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            try {
                file.createNewFile();
                // Initialize file with an empty JSON array if it's newly created
                objectMapper.writeValue(file, new ArrayList<>());
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    public void writeToFile(Object data) throws IOException {
        if (mode.equalsIgnoreCase("r")) {
            System.out.println("Mode was set to 'read' while trying to 'write'; it is now set to 'append'.");
            this.setMode("a");
        }
        if (mode.equalsIgnoreCase("a")) {
            // Append to existing JSON array
            try {
                List<Object> existingData = readFromFileAsList();
                if (existingData == null) {
                    existingData = new ArrayList<>();
                }
                existingData.add(data); // Add new object to the list
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), existingData);
            } catch (IOException e) {
                throw new IOException("Error appending to JSON file: " + e.getMessage());
            }
        } else if (mode.equalsIgnoreCase("w")) {
            // Overwrite the file with new data
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), data);
            } catch (IOException e) {
                throw new IOException("Error writing to JSON file: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Invalid mode.");
        }
    }

    public <T> T readFromFile(TypeReference<T> typeReference) throws IOException {
        if (mode.equalsIgnoreCase("a") || mode.equalsIgnoreCase("w")) {
            System.out.println("Mode was set to 'write/append' while trying to 'read'; it is now set to 'read'.");
            this.setMode("r");
        }
        if (mode.equalsIgnoreCase("r")) {
            try {
                return objectMapper.readValue(new File(fileName), typeReference);
            } catch (IOException e) {
                throw new IOException("Error reading JSON file: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Invalid mode.");
        }
    }

    public List<Object> readFromFileAsList() throws IOException {
        if (mode.equalsIgnoreCase("a") || mode.equalsIgnoreCase("w")) {
            System.out.println("Mode was set to 'write/append' while trying to 'read'; it is now set to 'read'.");
            this.setMode("r");
        }
        if (mode.equalsIgnoreCase("r")) {
            try {
                File file = new File(fileName);
                if (file.length() == 0) {
                    return new ArrayList<>();
                }
                return objectMapper.readValue(file, new TypeReference<List<Object>>() {
                });
            } catch (IOException e) {
                throw new IOException("Error reading JSON file: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Invalid mode.");
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
