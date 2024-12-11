package connecthub.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.DataBaseManager;
import connecthub.entities.MembershipRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MembershipRequestMapper {

    // Static database file path
    private static final String DATABASE_FILE = "membershipRequests.json";

    // Set the database file for DataBaseManager during class loading
    static { DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE); }

    // Create a new membership request
    public static void create(MembershipRequest membershipRequest) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            DataBaseManager.getDBM().createEntityWithID(membershipRequest);
        } catch (IOException e) {
            System.out.println("Error adding membership request to the database: " + e.getMessage());
        }
    }

    // Retrieve all membership requests
    public static List<MembershipRequest> getAll() {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().readEntities(new TypeReference<List<MembershipRequest>>() { });
        } catch (IOException e) {
            System.out.println("Error retrieving all membership requests: " + e.getMessage());
            return List.of();
        }
    }
    
    // Retrieve all pending membership requests for a specific group
    public static List<MembershipRequest> getAllForGroup(int groupID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<MembershipRequest> membershipRequests = getAll();
            return membershipRequests.stream()
                    .filter(request -> request.getGroupID() == groupID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving membership requests for group " + groupID + ": " + e.getMessage());
            return List.of();
        }
    }
    
    // Retrieve all pending membership requests for a specific user
    public static List<MembershipRequest> getAllForUser(int userID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<MembershipRequest> membershipRequests = getAll();
            return membershipRequests.stream()
                    .filter(request -> request.getUserID() == userID)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving membership requests for user " + userID + ": " + e.getMessage());
            return List.of();
        }
    }

    // Retrieve a specific membership request by ID
    public static Optional<MembershipRequest> get(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<MembershipRequest> membershipRequests = getAll();
            return membershipRequests.stream().filter(membershipRequest -> membershipRequest.getID() == id).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving membership request: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    // Retrieve a request by groupID and userID
    public static Optional<MembershipRequest> get(int groupID, int userID) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            List<MembershipRequest> requests = getAll();
            return requests.stream().filter(request -> request.getGroupID() == groupID && request.getUserID() == userID).findFirst();
        } catch (Exception e) {
            System.out.println("Error retrieving request by groupID and userID: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Update a specific membership request
    public static boolean update(int id, MembershipRequest updatedMembershipRequest) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            updatedMembershipRequest.setID(id);

            return DataBaseManager.getDBM().updateEntity(
                    new TypeReference<List<MembershipRequest>>() { },
                    updatedMembershipRequest,
                    membershipRequest -> membershipRequest.getID() == id
            );

        } catch (IOException e) {
            System.out.println("Error updating membership request: " + e.getMessage());
            return false;
        }
    }

    // Delete a specific membership request
    public static boolean delete(int id) {
        DataBaseManager.getDBM().setDataBaseFile(DATABASE_FILE);
        try {
            return DataBaseManager.getDBM().deleteEntity(
                    new TypeReference<List<MembershipRequest>>() { },
                    membershipRequest -> membershipRequest.getID() == id
            );
        } catch (IOException e) {
            System.out.println("Error deleting membership request: " + e.getMessage());
            return false;
        }
    }
}
