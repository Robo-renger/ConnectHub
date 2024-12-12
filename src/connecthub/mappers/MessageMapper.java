package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Chat;
import connecthub.entities.Friend;
import connecthub.entities.Message;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MessageMapper {

//    Static database file path
    private static final String DATABASE_FILE = "messages.json";

//    Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

//    Create a new profile
    public static void create(Message message) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(message);
        } catch (IOException e) {
            System.out.println("Error adding message to the data base: " + e.getMessage());
        }
    }

//    Retrieve all Messages
    public static List<Message> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Message>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all message: " + e.getMessage());
            return List.of();
        }
    }

    public static Optional<Message> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Message> messages = getAll();
            return messages.stream().filter(message -> message.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving message: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static boolean update(int id, Message updatedMessage) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedMessage.setID(id);
            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Message>>() {
            },
                    updatedMessage,
                    message -> message.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating message: " + e.getMessage());
            return false;
        }
    }

    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Message>>() {
            }, message -> message.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting message: " + e.getMessage());
            return false;
        }
    }
}
