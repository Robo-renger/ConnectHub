package connecthub;

import connecthub.entities.Notification;
import connecthub.interfaces.Observer;
import java.util.ArrayList;
import java.util.List;

public class NotificationProducer {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            new Thread(() -> observer.update(notification)).start(); // Notify each observer in a separate thread
        }
    }
}
