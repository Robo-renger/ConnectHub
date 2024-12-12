package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.mappers.CommentMapper;

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

    public int like(int postId) {
        this.likesCount++;
        return this.likesCount;
    }

    public int getLikesCount(int postId) {
        return this.likesCount;
    }
    public int getCommentsCount(int postId){
        return this.commentsCount;
    }
    public int comment(int userId, int postId, String commentContent) {
        Comment newComment = new Comment(userId, postId, commentContent);
        CommentMapper.create(newComment);
        this.commentsCount++;
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
