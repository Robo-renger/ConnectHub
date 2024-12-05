package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;
import javax.swing.ImageIcon;

@JsonTypeName("Profile")
public class Profile implements Identifiable {

//    Attributes
    private int id; // Profile unique ID 
    private int userID; // User's ID related to the profile
    private String bio;
    private String profilePhotoPath;
    private String coverPhotoPath;
    private static final String TYPE = "Profile";
    
//    Default constructor for Jackson
    public Profile() {} 

    public Profile(int userID, String bio, String profilePhotoPath, String coverPhotoPath) {
        this.userID = userID;
        this.bio = bio;
        this.profilePhotoPath = profilePhotoPath;
        this.coverPhotoPath = coverPhotoPath;
    }
    
//    Getters
    @Override
    public int getID() {
        return id;
    }
    
    public int getUserID()
    {
        return userID;
    }
    
    public String getProfilePhotoPath()
    {
        return profilePhotoPath;
    }
    
    public String getCoverPhotoPath()
    {
        return coverPhotoPath;
    }
    
    public String getBio()
    {
        return bio;
    }

    public String getType() {
        return TYPE;
    }
    
//    Setters
    @Override
    public void setID(int id) {
        this.id = id;
    }
    
    public void setProfilePhotoPath(String data)
    {
        profilePhotoPath = data;
    }
    
    public void setCoverPhotoPath(String data)
    {
        coverPhotoPath = data;
    }
    
    public void setBio(String data)
    {
        bio = data;
    }
  
}
