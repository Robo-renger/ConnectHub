package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Chat;
import connecthub.entities.Comment;
import connecthub.entities.Friend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CommentMapper {

//    Static database file path
    private static final String DATABASE_FILE = "comments.json";

//    Set the database file for DataBaseManager during class loading
    static {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    }

//    Create a new profile
    public static int create(Comment comment) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().createEntityWithID(comment);
        } catch (IOException e) {
            System.out.println("Error adding chat to the data base: " + e.getMessage());
        }
        return -1;
    }

//    Retrieve all Profiles
    public static List<Comment> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Comment>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all comments: " + e.getMessage());
            return List.of();
        }
    }

    public static Optional<Comment> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Comment> comments = getAll();
            return comments.stream().filter(comment -> comment.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving comment: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<Comment> getPostComments(int postId) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<Comment> comments = getAll();
            return comments.stream().filter(comment -> comment.getPostId() == postId).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving Comment: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static boolean update(int id, Comment updatedComment) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedComment.setID(id);
            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Comment>>() {
            },
                    updatedComment,
                    chat -> chat.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating comment: " + e.getMessage());
            return false;
        }
    }

//    Delete a specific user's profile
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Comment>>() {
            }, comment -> comment.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting comment: " + e.getMessage());
            return false;
        }
    }
}
