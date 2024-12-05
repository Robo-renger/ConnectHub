package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Post") // Matches the type name in @JsonSubTypes
public class Post extends Content {

    private static final String TYPE = "Post";

    public Post() {
    } // Default constructor for Jackson}

    public Post(int authorId, String content) {
        super(authorId, content);
    }

    @Override
    public boolean isExpired() {
        super.setExpiry(false);
        return false;
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
                + ", content='" + super.getContent() + '\''
                + ", timestamp=" + super.getTimestamp()
                + '}';
    }
}
