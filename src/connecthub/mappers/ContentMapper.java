package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.Content;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ContentMapper {

//    Static database file path
    private static final String DATABASE_FILE = "content.json"; 
    
//    Set the database file for DataBaseManager during class loading
    static {DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);}

//    Create a new content (Post or Story)
    public static void create(Content content) {
        try {
            DataBaseManager.getDBM().createEntityWithID(content);
        } catch (IOException e) {
            System.out.println("Error creating content: " + e.getMessage());
        }
    }
    
//    Retrieve all content (Posts and Stories)
    public static List<Content> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<Content>>() {}); 
        } catch (IOException e) {
            System.out.println("Error retrieving all content: " + e.getMessage());
            return List.of(); 
        }    
    }
    
//    Retrieve a specific content by content ID (Post or Story)
    public static Optional<Content> get(int contentId) {
        try {
            List<Content> contentList = getAll();
            return contentList.stream().filter(content -> content.getID() == contentId).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving content: " + e.getMessage());
            return Optional.empty();
        }
    }
    
//    Update a specific content by ID (Post or Story)
    public static boolean update(int id, Content updatedContent) {
        try {
            updatedContent.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<Content>>() {},
                    updatedContent,
                    content -> content.getID() == id
            );
            
        } catch (IOException e) {
            System.out.println("Error updating content: " + e.getMessage());
            return false;
        }
    }
    
//    Delete a specific content by ID (Post or Story)
    public static boolean delete(int id) {
        try {
            return DataBaseManager.getDBM().deleteEntity(new TypeReference<List<Content>>() {
            }, content -> content.getID() == id);
        } catch (IOException e) {
            System.out.println("Error deleting content: " + e.getMessage());
            return false;
        }
    }
}
