/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub.builders;

import connecthub.entities.ContentType;
import connecthub.entities.User;
import connecthub.interfaces.Builder;
import connecthub.mappers.UserMapper;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class UserBuilder implements Builder<User> {

    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private String status;
    private static UserBuilder instance;

    // Private constructor to prevent instantiation
    private UserBuilder() {
    }

    // Public method to get the single instance
    public static UserBuilder getInstance() {
        if (instance == null) {
            instance = new UserBuilder();
        }
        return instance;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public User build() {
        User user = new User(email, username, password, dateOfBirth);
        UserMapper.create(user);
        return user;
    }

}
