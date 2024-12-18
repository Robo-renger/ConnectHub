package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import connecthub.interfaces.Identifiable;
import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "authorId", "content", "imagePath", "timestamp", "expired", "type"})
public abstract class Content implements Identifiable {

    // Attributes
    private int contentId;
    private int authorId;  // Reference to the user who created it
    private String content; // Caption
    private String imagePath; // Image
    private boolean expired;
    

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp; // Creation time

    public Content() {
    } // Default constructor for Jackson

    public Content(int authorId, String content, String imagePath) {
        this.authorId = authorId;
        this.content = content;
        this.imagePath = imagePath;
        this.timestamp = LocalDateTime.now();
    }

    // Getters 
    @Override
    public int getID() {
        return contentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters 
    @Override
    public void setID(int contentId) {
        this.contentId = contentId;
    }

    public void setContent(String content) {
        System.out.println("Content");
        this.content = content;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setExpiry(boolean expired) {
        this.expired = expired;
    }

    // Abstract method for handling expiration of stories
    public abstract boolean isExpired();
}
