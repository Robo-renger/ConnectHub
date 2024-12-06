package connecthub.builders;

import connecthub.entities.Profile;
import connecthub.interfaces.Builder;
import connecthub.mappers.ProfileMapper;

public class ProfileBuilder implements Builder<Profile> {

    private int userID;
    private String bio;
    private String profilePhotoPath;
    private String coverPhotoPath;
    private static ProfileBuilder instance;

    // Private constructor to prevent instantiation
    private ProfileBuilder() {
    }

    // Public method to get the single instance
    public static ProfileBuilder getInstance() {
        if (instance == null) {
            instance = new ProfileBuilder();
        }
        return instance;
    }

    // Setters for all attributes of Profile
    public ProfileBuilder setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public ProfileBuilder setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public ProfileBuilder setProfilePhotoPath(String profilePhotoPath) {
        if (profilePhotoPath.equals("")) {
            this.profilePhotoPath = "src/assets/default-profile.png";
        } else {
            this.profilePhotoPath = profilePhotoPath;
        }
        return this;
    }

    public ProfileBuilder setCoverPhotoPath(String coverPhotoPath) {
        if (coverPhotoPath.equals("")) {
            this.coverPhotoPath = "src/assets/default-cover.jpg";
        } else {
            this.coverPhotoPath = coverPhotoPath;
        }
        return this;
    }

    // Build method to construct the Profile object
    @Override
    public Profile build() throws IllegalArgumentException {
        Profile profile = new Profile(userID, bio, profilePhotoPath, coverPhotoPath);
        try {
            ProfileMapper.create(profile);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return profile;
    }
}
