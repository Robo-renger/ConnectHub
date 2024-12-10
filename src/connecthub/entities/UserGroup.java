package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;
import java.util.List;
import javax.swing.ImageIcon;

@JsonTypeName("UserGroup")
public class UserGroup implements Identifiable {

//    Attributes
    private int id; // Profile unique ID 
    private int userID; // User's ID related to the profile
    private int groupID;
    private String status;
    private static final String TYPE = "UserGroup";

//    Default constructor for Jackson
    public UserGroup() {}

    public UserGroup(int groupID, int userID) {
        this.groupID = groupID;
        this.userID = userID;
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

    @Override
    public String toString() {
        return "UserGroup {"
                + "Id='" + id + '\''
                + "userID='" + userID + '\''
                + ", groupID='" + groupID + '\''
                + '}';
    }

}
