package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("Comment") // Matches the type name in @JsonSubTypes
public class Comment implements Identifiable {

    // Attributes
    private int id;
    private int userId;
    private int postId;
    private String content;
    private static final String TYPE = "Comment";

    public Comment() {
    } // Default constructor for Jackson

    public Comment(int userId, int postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    // Getters & Setters
    @Override
    public int getID() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }
    public String getContent() {
        return content;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
