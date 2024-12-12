package connecthub;

import connecthub.entities.Message;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import connecthub.mappers.ChatMapper;
import connecthub.mappers.MessageMapper;

public class ChatWatcher {

    private final Set<Message> seenMessages = new HashSet<>(); // To track already fetched messages
    private final List<Message> newMessages = new ArrayList<>(); // To store newly fetched messages
    private boolean running = true;

    public ChatWatcher() {
    }

    public void startWatching(int userOneId, int userTwoId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (running) {
                try {
                    fetchNewMessages(userOneId, userTwoId);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ChatWatcher interrupted: " + e.getMessage());
                }
            }
        });
    }

    private synchronized void fetchNewMessages(int userOneId, int userTwoId) {
        List<Message> allMessages = MessageMapper.getAll(userOneId, userTwoId);
        for (Message message : allMessages) {
            newMessages.add(message);
        }
    }

    public synchronized List<Message> getNewMessages() {
        List<Message> messagesToReturn = new ArrayList<>(newMessages);
        newMessages.clear();
        return messagesToReturn;
    }

    public void stopWatching() {
        running = false;
    }
}
