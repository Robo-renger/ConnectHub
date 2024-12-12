package connecthub;

import connecthub.entities.Notification;
import connecthub.interfaces.Observer;
import connecthub.server.LongPollingServer;

public class Notifier implements Observer {

    private final LongPollingServer server;

    public Notifier(LongPollingServer server) {
        this.server = server;
    }
    
    @Override
    public void update(Notification notification) {
        server.addNotification(notification);
    }
}
