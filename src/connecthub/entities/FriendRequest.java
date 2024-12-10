package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("FriendRequest") // Matches the type name in @JsonSubTypes
public class FriendRequest implements Identifiable{

    //Attributes
    private int id;
    private int senderId;
    private int receiverId;
    private String status; // for the 3 status [Pending,Accepted,Rejected]
    private static final String TYPE = "FriendRequest";
    
    public FriendRequest() {} // Default constructor for Jackson

    public FriendRequest(int senderId, int receiverId, String status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }
    
    // Getters and Setters
    @Override
    public int getID() {
        return id;
    }
    
    public int getSenderId()
    {
        return senderId;
    }
    
    public int getReceiverId()
    {
        return receiverId;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    
}
