package connecthub.entities;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.interfaces.Identifiable;
import connecthub.mappers.UserGroupMapper;

@JsonTypeName("Group")
public class Group implements Identifiable {

//    Attributes
    private int id; // Group unique ID 
    private int creatorID;
    private String name;
    private String imagePath;
    private String description;
    private static final String TYPE = "Group";

//    Default constructor for Jackson
    public Group() {
    }

    public Group(int creatorID, String name, String description, String imagePath) {
        this.creatorID = creatorID;
        this.name = name;
        this.description = description;
        setImagePath(imagePath);
    }

    public Group(int creatorID, String name, String description) {
        this.creatorID = creatorID;
        this.name = name;
        this.description = description;
        setImagePath("");
    }

//    Getters
    @Override
    public int getID() {
        return id;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return TYPE;
    }

//    Setters
    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setCreatorId(int creatorID) {
        this.creatorID = creatorID;
    }

    public void setImagePath(String data) {
        if (data.equals("")) {
            imagePath = "src/assets/default-group.png";
        } else {
            imagePath = data;
        }
    }

    public void setName(String data) {
        name = data;
    }

    public void setDescription(String data) {
        description = data;
    }

    public void creatorGroup() {
        if (id != 0) {
            UserGroup creatorGroup = new UserGroup(id, creatorID);
            creatorGroup.setRole("CREATOR");
            UserGroupMapper.create(creatorGroup);
        }
    }

    @Override
    public String toString() {
        return "Group {"
                + "Id='" + id + '\''
                + "Name='" + name + '\''
                + ", ImagePath='" + imagePath + '\''
                + ", Description='" + description + '\''
                + '}';
    }

}
