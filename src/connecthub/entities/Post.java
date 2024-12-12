package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.mappers.CommentMapper;
import connecthub.mappers.ContentMapper;

@JsonTypeName("Post") // Matches the type name in @JsonSubTypes
public class Post extends Content {

    private static final String TYPE = "Post";
    private int likesCount = 0;
    private int commentsCount = 0;

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

    public int comment(int userId, String commentContent) {
        Comment newComment = new Comment(userId, this.getID(), commentContent);
        CommentMapper.create(newComment);
        this.commentsCount++;
        ContentMapper.update(this.getID(), this);
        return this.commentsCount;
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
