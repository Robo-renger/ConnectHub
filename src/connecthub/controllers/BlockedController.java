package connecthub.controllers;

import connecthub.entities.Blocked;
import connecthub.mappers.BlockedMapper;
import java.util.List;
import java.util.stream.Collectors;

public class BlockedController {
    
    /***
     * @param userId 
     * @return All Blocked users for a specific user 
     */
    public static List<Blocked> getAllBlocks(int userId)
    {
        List<Blocked> blocks = BlockedMapper.getAll();
        if (blocks == null) 
            return List.of();
        
        return blocks.stream()
               .filter(blocked -> blocked.getUserId()== userId || blocked.getBlockedId()== userId).collect(Collectors.toList());
    }
    
}
