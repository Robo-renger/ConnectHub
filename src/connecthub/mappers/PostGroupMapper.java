package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.PostGroup;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostGroupMapper {

//    Static database file path
    private static final String DATABASE_FILE = "groupsPosts.json";

//    Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}

//    Create a new Post 
    public static void create(PostGroup postGroup) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(postGroup);
        } catch (IOException e) {
            System.out.println("Error creating post: " + e.getMessage());
        }
    }

//    Retrieve all content (Posts and Stories)
    public static List<PostGroup> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<PostGroup>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all Posts: " + e.getMessage());
            return List.of();
        }
    }

//    Retrieve a specific Post by entity's ID
    public static Optional<PostGroup> get(int postGroupID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<PostGroup> postGroups = getAll();
            return postGroups.stream().filter(content -> content.getID() == postGroupID).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving post: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    // Retrieve all PostGroup entities by groupID
    public static List<PostGroup> getAllGroupPosts(int groupID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<PostGroup> postGroups = getAll();

            // Filter the PostGroup entities by groupID
            return postGroups.stream()
                    .filter(postGroup -> postGroup.getGroupID() == groupID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving all the group's posts: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }
    
    // Retrieve all PostGroup entities by userID
    public static List<PostGroup> getAllMemberPosts(int groupID, int userID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<PostGroup> postGroups = getAll();

            // Filter the PostGroup entities by groupID and userID
            return postGroups.stream()
                    .filter(postGroup -> postGroup.getGroupID() == groupID && postGroup.getAuthorId() == userID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving all the user's posts: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }

//    Update a specific post
    public static boolean update(int id, PostGroup updatedPostGroup) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedPostGroup.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<PostGroup>>() {
            },
                    updatedPostGroup,
                    postGroup -> postGroup.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating post: " + e.getMessage());
            return false;
        }
    }

//    Delete a specific post
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<PostGroup>>() {
            }, postGroup -> postGroup.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting post: " + e.getMessage());
            return false;
        }
    }
}
