package connecthub.interfaces;

import connecthub.entities.Notification;

// Observer Behavioural Design Pattern
public interface Observer {
    void update(Notification notification);
}

