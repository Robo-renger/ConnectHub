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

    public void startWatching() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (running) {
                try {
                    fetchNewMessages();
                    Thread.sleep(1000); // Poll every second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ChatWatcher interrupted: " + e.getMessage());
                }
            }
        });
    }

    private synchronized void fetchNewMessages() {
        List<Message> allMessages = MessageMapper.getAll();
        for (Message message : allMessages) {
//            if (!seenMessages.contains(message)) {
//            seenMessages.add(message); // Mark as seen
            newMessages.add(message); // Add to new messages
            System.out.println("New message: " + message); // Print new message (or handle it as needed)
//            }
        }
    }

    public synchronized List<Message> getNewMessages() {
        List<Message> messagesToReturn = new ArrayList<>(newMessages);
        newMessages.clear(); // Clear the new messages list after returning
        return messagesToReturn;
    }

    public void stopWatching() {
        running = false;
    }
}
