package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("Chat") // Matches the type name in @JsonSubTypes
public class Chat implements Identifiable {

    // Attributes
    private int id;
    private int userOneId;
    private int userTwoId;
    private static final String TYPE = "Chat";

    public Chat() {
    } // Default constructor for Jackson

    public Chat(int userOneId, int userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    // Getters & Setters
    @Override
    public int getID() {
        return id;
    }

    public int getUserOneId() {
        return userOneId;
    }

    public int getUserTwoId() {
        return userTwoId;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setUserOneId(int userOneId) {
        this.userOneId = userOneId;
    }

    public void setUserTwoId(int userTwoId) {
        this.userTwoId = userTwoId;
    }

}
