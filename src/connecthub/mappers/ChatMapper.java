package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Chat;
import connecthub.entities.Friend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ChatMapper {

//    Static database file path
    private static final String DATABASE_FILE = "chats.json";

//    Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

//    Create a new profile
    public static int create(Chat chat) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().createEntityWithID(chat);
        } catch (IOException e) {
            System.out.println("Error adding chat to the data base: " + e.getMessage());
        }
        return -1;
    }

//    Retrieve all Profiles
    public static List<Chat> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Chat>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all chat: " + e.getMessage());
            return List.of();
        }
    }

    public static Optional<Chat> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Chat> chats = getAll();
            return chats.stream().filter(chat -> chat.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrievingchat: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<Chat> get(int userOneId, int userTwoId) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Chat> chats = getAll();
            return chats.stream().filter(chat -> chat.getUserOneId() == userOneId && chat.getUserTwoId() == userTwoId
                    || chat.getUserOneId() == userTwoId && chat.getUserTwoId() == userOneId).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrievingchat: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static boolean update(int id, Chat updatedChat) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedChat.setID(id);
            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Chat>>() {
            },
                    updatedChat,
                    chat -> chat.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating chat: " + e.getMessage());
            return false;
        }
    }

//    Delete a specific user's profile
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Chat>>() {
            }, chat -> chat.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting chat: " + e.getMessage());
            return false;
        }
    }
}
