package connecthub.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Use a type name for mapping
        include = JsonTypeInfo.As.PROPERTY, // Include the type name as a property in JSON
        property = "type" // Property name that specifies the type
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = connecthub.entities.User.class, name = "User"), // User entity
//    @JsonSubTypes.Type(value = connecthub.entities.Admin.class, name = "Admin"), // Example Admin entity
//    @JsonSubTypes.Type(value = connecthub.entities.Customer.class, name = "Customer") // Example Customer entity
})
public interface Identifiable {

    int getID();

    void setID(int id);
}
