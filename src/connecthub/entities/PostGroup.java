package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PostGroup") // Matches the type name in @JsonSubTypes
@JsonPropertyOrder({ "id", "authorId", "groupID", "content", "imagePath", "timestamp", "expired", "type" })
public class PostGroup extends Post {

    private int groupID;
    private static final String TYPE = "PostGroup";

    public PostGroup() {} // Default constructor for Jackson}

    public PostGroup(int authorID, int groupID, String content, String imagePath) {
        super(authorID, content, imagePath);
        this.groupID = groupID;
    }

    @Override
    public boolean isExpired() {
        super.setExpiry(false);
        return false;
    }

    public int getGroupID()
    {
        return this.groupID;
    }
    
    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "Post {"
                + "contentId=" + super.getID()
                + ", authorId=" + super.getAuthorId()
                + ", groupId =" + groupID
                + ", content='" + super.getContent() + '\''
                + ", timestamp=" + super.getTimestamp()
                + '}';
    }
}
