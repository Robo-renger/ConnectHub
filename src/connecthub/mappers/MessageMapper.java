package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.NotificationManager;
import connecthub.entities.Chat;
import connecthub.entities.Friend;
import connecthub.entities.Message;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            if(message.getStatus().equalsIgnoreCase("sent"))
            {
                DataBaseManager.getDBM().setDataBaseFile("notifications.json");
                NotificationManager notificationManager = new NotificationManager();
                notificationManager.sendNotification(message.getRecieverId(), "Chat", "You have a new message from " + message.getSenderId());
            }
        } catch (IOException e) {
            System.out.println("Error adding message to the data base: " + e.getMessage());
        }
    }

//    Retrieve all Messages
    public static List<Message> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Message>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all message: " + e.getMessage());
            return List.of();
        }
    }

    public static List<Message> getAll(int userOneId, int userTwoId) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Message> messages = getAll();

            // Filter the UserGroup entities by userID and status
            return messages.stream()
                    .filter(message -> message.getSenderId() == userOneId && message.getRecieverId() == userTwoId
                    || message.getSenderId() == userTwoId && message.getRecieverId() == userOneId)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving user groups by user's ID and status: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
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
