package connecthub.server;

import connecthub.entities.Notification;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LongPollingServer {
    private final BlockingQueue<Notification> notificationQueue = new LinkedBlockingQueue<>();

    public void addNotification(Notification notification) {
        notificationQueue.add(notification);
    }

    public Notification waitForNotification() throws InterruptedException {
        return notificationQueue.take(); // Blocks until a notification is available
    }
}
