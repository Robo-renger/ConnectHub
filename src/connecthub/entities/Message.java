package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import connecthub.interfaces.Identifiable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonTypeName("Message") // Matches the type name in @JsonSubTypes
public class Message implements Identifiable {

    // Attributes
    private int id;
    private int senderId;
    private int recieverId;
    private int chatId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    private String status;
    private String content;
    private static final String TYPE = "Message";

    public Message() {
    } // Default constructor for Jackson

    public Message(int senderId, int recieverId, int chatId, String content) {
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.chatId = chatId;
        this.status = "sent";
        this.content = content;
        this.date = LocalDateTime.now();
    }

    // Getters & Setters
    @Override
    public int getID() {
        return id;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getChatId() {
        return chatId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getStatus() {
        return status;
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

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{"
                + "id=" + id
                + ", senderId='" + senderId + '\''
                + ", recieverId ='" + recieverId + '\''
                + ", content='" + content + '\''
                + ", status=" + status + '\''
                + ", date=" + date
                + '}';
    }
}
