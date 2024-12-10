package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("Friend") // Matches the type name in @JsonSubTypes
public class Friend implements Identifiable{

    // Attributes
    private int id;
    private int userId;
    private int friendId;
    private static final String TYPE = "Friend";

    public Friend() {} // Default constructor for Jackson
    
    public Friend(int userId, int friendId)
    {
        this.userId = userId;
        this.friendId = friendId;
    }
    
    // Getters & Setters
    @Override
    public int getID() {
        return id;
    }
    
    public int getUserId()
    {
        return userId;
    }
    
    public int getFriendId()
    {
        return friendId;
    }
    
    @Override
    public String getType() {
        return TYPE;
    }
    
    @Override
    public void setID(int id) {
        this.id = id;
    }

}
