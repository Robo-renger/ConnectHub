package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("MembershipRequest") // Matches the type name in @JsonSubTypes
public class MembershipRequest implements Identifiable{

    //Attributes
    private int id;
    private int userID;
    private int groupID;
    private String status; // Pending, Accepted, Rejected
    private static final String TYPE = "MembershipRequest";
    
    public MembershipRequest() {} // Default constructor for Jackson

    public MembershipRequest(int groupID, int userID) {
        this.groupID = groupID; 
        this.userID = userID;
        this.status = "PENDING";
    }

    // Getters 
    @Override
    public int getID() {
        return id;
    }
    
    public int getUserID() {
        return userID;
    }

    public int getGroupID() {
        return groupID;
    }
    
    public String getStatus(){
        return status;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    
    // Setters
    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
