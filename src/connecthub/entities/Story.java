package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@JsonTypeName("Story") // Matches the type name in @JsonSubTypes
public class Story extends Content{

    private static final String TYPE = "Story";
    
    public Story() {} // Default constructor for Jackson
    
    public Story(int authorId, String content) {
        super(authorId, content);
    }

    @Override
    public boolean isExpired() {
        boolean expiry = getTimestamp().plus(24, ChronoUnit.HOURS).isBefore(LocalDateTime.now());
        super.setExpiry(expiry);
        return expiry;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    
}
