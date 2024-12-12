package connecthub;

import connecthub.entities.Notification;
import connecthub.mappers.NotificationMapper;
import connecthub.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private final List<Observer> observers = new ArrayList<>();

    // Add an observer to the list
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer from the list
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers about the new notification
    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

    public void sendNotification(int recipientID, String type, String message) {
        // Create the notification based on the provided details
        Notification notification = new Notification(recipientID, type, message);
        
        // Store the notification in the database
        NotificationMapper.create(notification);
        
        // Notify all observers (users) about the new notification
        notifyObservers(notification);
    }
    
    // Check for new notifications and notify observers
    public void checkAndNotify() {
        List<Notification> notifications = NotificationMapper.getAll();

        for (Notification notification : notifications) {
            if (!notification.isRead()) {
                // Notify observers about the new notification
                notifyObservers(notification);

                // Mark the notification as read after notifying
                notification.setRead(true);
                NotificationMapper.update(notification.getID(), notification);
            }
        }
    }
}