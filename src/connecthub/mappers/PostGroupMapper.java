package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Post;
import connecthub.entities.PostGroup;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PostGroupMapper {

//    Static database file path
    private static final String DATABASE_FILE = "groupsPosts.json";

//    Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}

//    Create a new post group relation 
    public static void create(PostGroup postGroup) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(postGroup);
        } catch (IOException e) {
            System.out.println("Error creating post group relation: " + e.getMessage());
        }
    }

//    Retrieve all post group relations
    public static List<PostGroup> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<PostGroup>>() {
            });
        } catch (IOException e) {
            System.out.println("Error retrieving all the post group relations: " + e.getMessage());
            return List.of();
        }
    }

//    Retrieve a specific post group relation by entity's ID
    public static Optional<PostGroup> get(int postGroupID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<PostGroup> postGroups = getAll();
            return postGroups.stream().filter(content -> content.getID() == postGroupID).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving the post group relation: " + e.getMessage());
            return Optional.empty();
        }
    }
    
//    Retrieve a specific post group relation by groupID and postID
    public static Optional<PostGroup> get(int groupID, int postID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<PostGroup> postGroups = getAll();
            return postGroups.stream().filter(postGroup -> postGroup.getGroupID() == groupID && postGroup.getPostID() == postID).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving the post group relation by groupID and postID: " + e.getMessage());
            return Optional.empty();
        }
    }
    
//    Retrieve a specific post group relation by postID
    public static Optional<PostGroup> getByPostID(int postID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<PostGroup> postGroups = getAll();
            return postGroups.stream().filter(postGroup -> postGroup.getPostID() == postID).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving the post group relation by postID: " + e.getMessage());
            return Optional.empty();
        }
    }
    
//    Retrieves all posts for the group
    public static List<Post> getAllPostsByGroupID(int groupID) {
    DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
    try {
        // Retrieve all PostGroup mappings
        List<PostGroup> postGroups = getAll();
        
        // Filter PostGroup records for the specific groupID and collect postIDs
        Set<Integer> postIDs = postGroups.stream()
                .filter(postGroup -> postGroup.getGroupID() == groupID)
                .map(PostGroup::getPostID)
                .collect(Collectors.toSet());
        
        // Retrieve all posts and filter those whose IDs match the collected postIDs
        List<Post> allPosts = ContentMapper.getAll().stream()
                .filter(content -> content instanceof Post) // Ensure only Post objects
                .map(content -> (Post) content)
                .filter(post -> postIDs.contains(post.getID()))
                .collect(Collectors.toList());

        return allPosts;

    } catch (Exception e) {
        System.out.println("Error retrieving posts for the group: " + e.getMessage());
        return List.of(); // Return an empty list in case of an error
    }
}

//    Update a specific post group relation
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
            System.out.println("Error updating the post group relation: " + e.getMessage());
            return false;
        }
    }

//    Delete a specific post group relation
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<PostGroup>>() {
            }, postGroup -> postGroup.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting the post group relation: " + e.getMessage());
            return false;
        }
    }
}
