package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("Blocked") // Matches the type name in @JsonSubTypes
public class Blocked implements Identifiable{

    // Attributes
    private int id;
    private int userId;
    private int blockedId;
    private static final String TYPE = "Blocked";

    public Blocked() {} // Default constructor for Jackson
    
    public Blocked(int userId, int blockedId)
    {
        this.userId = userId;
        this.blockedId = blockedId;
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
    
    public int getBlockedId()
    {
        return blockedId;
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
