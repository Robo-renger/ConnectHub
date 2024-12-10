package connecthub.controllers;

import connecthub.entities.Content;
import connecthub.mappers.ContentMapper;
import java.util.List;
import java.util.stream.Collectors;

public class ContentController {
    
    /***
     * @param userId 
     * @return All contents for a specific user 
     */
    public static List<Content> getAllContents(int userId)
    {
        List<Content> contents = ContentMapper.getAll();
        if (contents == null) 
            return List.of();
        
        return contents.stream()
               .filter(content -> content.getAuthorId() == userId).collect(Collectors.toList());
    }
    
    /***
     * @param userId 
     * @return All posts for a specific user 
     */
    public static List<Content> getAllPosts(int userId)
    {
        List<Content> contents = ContentMapper.getAll();
        if (contents == null) 
            return List.of();
        
        return contents.stream()
               .filter(content -> content.getType().equalsIgnoreCase("Post")
               && content.getAuthorId() == userId).collect(Collectors.toList());
    }
    
    /***
     * @param userId 
     * @return All Stories for a specific user 
     */
    public static List<Content> getAllStories(int userId)
    {
        List<Content> contents = ContentMapper.getAll();
        if (contents == null) 
            return List.of();
        
        return contents.stream()
               .filter(content -> content.getType().equalsIgnoreCase("Story")
               && content.getAuthorId() == userId).collect(Collectors.toList());
    }
}
