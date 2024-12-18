package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.mappers.CommentMapper;
import connecthub.mappers.ContentMapper;

@JsonTypeName("Post") // Matches the type name in @JsonSubTypes
@JsonPropertyOrder({"id", "authorId", "groupID", "content", "imagePath", "timestamp", "expired", "type", "likesCount", "commentsCount"})
public class Post extends Content {

    private static final String TYPE = "Post";
    private int likesCount;
    private int commentsCount;

    public Post() {
    } // Default constructor for Jackson}

    public Post(int authorId, String content, String imagePath) {
        super(authorId, content, imagePath);
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
    
    public int like() {
        this.likesCount++;
        ContentMapper.update(this.getID(), this);
        return this.likesCount;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public int getCommentsCount() {
        return this.commentsCount;
    }

    public void setLikesCount(int likesCount) {
        System.out.println("Likes");
        this.likesCount = likesCount;
    }

    public void setCommentsCount(int commentsCount) {
        System.out.println("Comments");
        this.commentsCount = commentsCount;
    }

    public int comment(int userId, String commentContent) {
        Comment newComment = new Comment(userId, this.getID(), commentContent);
        CommentMapper.create(newComment);
        System.out.println("ana hena: " + this.commentsCount);
        this.commentsCount++;
        System.out.println("ana hena aho: " + this.commentsCount);
        ContentMapper.update(this.getID(), this);
        return this.commentsCount;
    }

    @Override
    public String toString() {
        return "Post {"
                + "contentId=" + super.getID()
                + ", authorId=" + super.getAuthorId()
                + ", content='" + super.getContent() + '\''
                + ", timestamp=" + super.getTimestamp() + '\''
                + ", likesCount=" + this.getLikesCount() + '\''
                + ", CommentsCount=" + this.getCommentsCount()
                + '}';
    }
}
