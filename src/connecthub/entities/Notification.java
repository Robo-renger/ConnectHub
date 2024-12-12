package connecthub.entities;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;

@JsonTypeName("Notification")
public class Notification implements Identifiable {

    // Attributes
    private int id; // Notification unique ID 
    private int recepientID; // User receiving the Notification
    private String notificationType; 
    private String message;
    private boolean read; //  Indication wheter the notification is read or not
    
    private static final String TYPE = "Notification";

    public Notification() {} // Default constructor for Jackson

    public Notification(int recepientID, String notificationType, String message) {
        this.recepientID = recepientID;
        this.notificationType = notificationType;
        this.message = message;
        this.read = false;
    }

    // Getters
    @Override
    public int getID() {
        return id;
    }
    
    public int getRecepientID()
    {
        return this.recepientID;
    }

    public String getNotificationType() {
        return this.notificationType;
    }
    
    public String getMessage()
    {
        return this.message;
    }

    public boolean isRead() {
        return this.read;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    // Setters
    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setRecepientID(int recepientID) {
        this.recepientID = recepientID;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    
    @Override
    public String toString() {
        return "Notification {"
                + "Id='" + id + '\''
                + "RecepientID='" + recepientID + '\''
                + ", Type='" + notificationType + '\''
                + ", IsRead='" + read + '\''
                + '}';
    }

}
