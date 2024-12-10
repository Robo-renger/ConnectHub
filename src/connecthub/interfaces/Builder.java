/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub.interfaces;

import connecthub.builders.UserBuilder;
import connecthub.entities.ContentType;
import connecthub.entities.User;

/**
 *
 * @author User
 */
public interface Builder<T> {

    T build(); // Method to construct the final object
    

}
