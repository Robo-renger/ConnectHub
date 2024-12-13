package connecthub;

import java.util.concurrent.TimeUnit;

public class NotificationService extends Thread {
    private final NotificationManager notificationManager;

    public NotificationService(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                notificationManager.checkAndNotify();
                TimeUnit.SECONDS.sleep(2); // Check every 5 seconds
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
