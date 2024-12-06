package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.mappers.ContentMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@JsonTypeName("Story") // Matches the type name in @JsonSubTypes
public class Story extends Content {

    private static final String TYPE = "Story";

    public Story() {
    } // Default constructor for Jackson

    public Story(int authorId, String content, String imagePath) {
        super(authorId, content, imagePath);
    }

    public boolean isExpired() {
        boolean expiry = getTimestamp().plus(24, ChronoUnit.HOURS).isBefore(LocalDateTime.now());
        super.setExpiry(expiry);
        
        if(expiry) ContentMapper.delete(this.getID());
        
        return expiry;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "Story {"
                + "contentId=" + super.getID()
                + ", authorId=" + super.getAuthorId()
                + ", content='" + super.getContent() + '\''
                + ", expired=" + isExpired()
                + ", timestamp=" + super.getTimestamp()
                + '}';
    }
}
