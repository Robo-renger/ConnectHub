package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.mappers.CommentMapper;
import connecthub.mappers.ContentMapper;

@JsonTypeName("PostGroup") // Matches the type name in @JsonSubTypes
@JsonPropertyOrder({"id", "authorId", "groupID", "content", "imagePath", "timestamp", "expired", "type", "likesCount", "commentsCount"})
public class PostGroup extends Post {

    private int groupID;
    private static final String TYPE = "PostGroup";
    private int likesCount;
    private int commentsCount;

    public PostGroup() {
    } // Default constructor for Jackson}

    public PostGroup(int authorID, int groupID, String content, String imagePath) {
        super(authorID, content, imagePath);
        this.groupID = groupID;
    }

    @Override
    public boolean isExpired() {
        super.setExpiry(false);
        return false;
    }

    public int getGroupID() {
        return this.groupID;
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
                + ", groupId =" + groupID
                + ", content='" + super.getContent() + '\''
                + ", timestamp=" + super.getTimestamp() + '\''
                + ", likesCount=" + this.getLikesCount() + '\''
                + ", CommentsCount=" + this.getCommentsCount()
                + '}';
    }
}
