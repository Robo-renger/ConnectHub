package connecthub.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import connecthub.*;
import java.time.LocalDate;

import connecthub.interfaces.Identifiable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@JsonTypeName("User") // Matches the type name in @JsonSubTypes
public class User implements Identifiable {

    private int id;
    private String email;
    private String username;
    private String password;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    private String status;
    private static final String type = "User";

    public User() {
        // Default constructor for Jackson
    }

    public User(String email, String username, String password, LocalDate dateOfBirth) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = "offline";
    }

    //GETTERS
    @Override
    public int getID() {
        return id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    //SETTERS
    @Override
    public void setID(int id) {
        this.id = id;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassword(String password) {
        try {
            this.password = PasswordHasher.hashPassword(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getType() {
        return User.type;
    }
}