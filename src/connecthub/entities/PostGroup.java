package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("PostGroup") // Matches the type name in @JsonSubTypes
public class PostGroup implements Identifiable{

    // Attributes
    private int id;
    private int postID;
    private int groupID;
    private static final String TYPE = "PostGroup";

    public PostGroup() {} // Default constructor for Jackson 

    public PostGroup(int groupID, int postID) {
        this.groupID = groupID;
        this.postID = postID;
    }

    @Override
    public int getID() {
        return this.id;
    }
    
    public int getPostID()
    {
        return this.postID;
    }
    
    public int getGroupID() {
        return this.groupID;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    
    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
    
    @Override
    public String toString() {
        return "Post {"
                + "id =" + id 
                + ", postID=" + postID
                + ", groupID =" + groupID
                + '}';
    }

}
