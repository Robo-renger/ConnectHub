package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("UserGroup")
public class UserGroup implements Identifiable {

//    Attributes
    private int id; // Entity unique ID 
    private int userID; // User's ID related to the Group's ID
    private int groupID; // Group's ID
    private String status; // joined, left
    private String role; // ADMIN, MEMBER
    private static final String TYPE = "UserGroup";

//    Default constructor for Jackson
    public UserGroup() {
    }
    
    public UserGroup(int groupID, int userID) {
        this.groupID = groupID;
        this.userID = userID;
        this.role = "MEMBER";
        this.status = "joined";
    }
    
//    Getters
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

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public String getType() {
        return TYPE;
    }

//    Setters
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

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "UserGroup {"
                + "Id='" + id + '\''
                + "userID='" + userID + '\''
                + ", groupID='" + groupID + '\''
                + ", role ='" + role + '\''
                + '}';
    }

}
