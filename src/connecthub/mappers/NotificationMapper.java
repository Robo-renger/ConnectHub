package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Notification;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotificationMapper {

    // Static database file path
    private static final String DATABASE_FILE = "notifications.json";

    // Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}

    // Create a new notification
    public static void create(Notification notification) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(notification);
        } catch (IOException e) {
            System.out.println("Error adding notification to the database: " + e.getMessage());
        }
    }

    // Retrieve all notifications
    public static List<Notification> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Notification>>() { });
        } catch (IOException e) {
            System.out.println("Error retrieving all notifications: " + e.getMessage());
            return List.of();
        }
    }
    
    // Retrieve all notifications for a specific recipient
    public static List<Notification> getAllForRecipient(int recepientID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Notification> notifications = getAll();
            return notifications.stream()
                    .filter(notification -> notification.getRecepientID() == recepientID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving notifications for recipient " + recepientID + ": " + e.getMessage());
            return List.of();
        }
    }

    // Retrieve unread notifications for a specific recipient
    public static List<Notification> getUnreadForRecipient(int recepientID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Notification> notifications = getAllForRecipient(recepientID);
            return notifications.stream()
                    .filter(notification -> !notification.isRead())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving unread notifications for recipient " + recepientID + ": " + e.getMessage());
            return List.of();
        }
    }
    
    // Retrieve all unread notifications 
    public static List<Notification> getAllUnread() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Notification> notifications = getAll();
            return notifications.stream()
                    .filter(notification -> !notification.isRead())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving unread notifications " + e.getMessage());
            return List.of();
        }
    }

    // Retrieve a specific notification by entity's ID
    public static Optional<Notification> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Notification> notifications = getAll();
            return notifications.stream().filter(notification -> notification.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving notification: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Update a specific notification
    public static boolean update(int id, Notification updatedNotification) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedNotification.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Notification>>() { },
                    updatedNotification,
                    notification -> notification.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating notification: " + e.getMessage());
            return false;
        }
    }

    // Delete a specific notification
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(
                    new TypeReference<List<Notification>>() { },
                    notification -> notification.getID() == id
            );
        } catch (IOException e) {
            System.out.println("Error deleting notification: " + e.getMessage());
            return false;
        }
    }
}
