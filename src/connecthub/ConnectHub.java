package connecthub;

import connecthub.entities.User;
import connecthub.builders.*;
import connecthub.mappers.UserMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.controllers.ContentController;
import connecthub.controllers.FriendController;
import connecthub.entities.Blocked;
import connecthub.entities.Chat;
import connecthub.entities.Content;
import connecthub.entities.ContentType;
import connecthub.entities.Friend;
import connecthub.entities.FriendRequest;
import connecthub.entities.Group;
import connecthub.entities.MembershipRequest;
import connecthub.entities.Message;
import connecthub.entities.Notification;
import connecthub.entities.Post;
import connecthub.entities.PostGroup;
import connecthub.entities.Profile;
import connecthub.entities.Story;
import connecthub.interfaces.Builder;
import connecthub.interfaces.Identifiable;
import connecthub.mappers.BlockedMapper;
import connecthub.mappers.ChatMapper;
import connecthub.mappers.ContentMapper;
import connecthub.mappers.FriendMapper;
import connecthub.mappers.GroupMapper;
import connecthub.mappers.MembershipRequestMapper;
import connecthub.mappers.PostGroupMapper;
import connecthub.mappers.MessageMapper;
import connecthub.mappers.ProfileMapper;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectHub {

    public static void main(String[] args) throws InvalidKeySpecException {
//        try {
        // TODO code application logic here

        // ########### User test cases ###########
        // Test Case 1: Create a new user
        //        testCreateUser();
        // Test Case 2: Retrieve all users
        //        testGetAllUsers();
        // Test Case 3: Delete a user by ID
        //        testDeleteUserById();
        // Test Case 4: Update an existing user
        //        testUpdateUser();
        // Test Case 5: Retrieve a user with filters
        //        testRetrieveUserWithFilters();
        // Test Case 6: Retrieve a user by ID
        //        testRetrieveUserById();
        //        System.out.println(Validator.validate(0, "user123@gmail.com"));
        //        System.out.println(Validator.validate(0, "User123@GMAIL.com"));
        // ########### Profile test cases ###########
        // Test Case 1: Create a new profile
        //        testCreateProfile();
        // Test Case 2: Retrieve all profiles
        //        testGetAllProfiles();
        // Test Case 3: Retrieve a profile by user ID
        //        testGetProfileByUserId();
        // Test Case 4: Update an existing profile
        //        testUpdateProfile();
        // Test Case 5: Delete a profile
        //        testDeleteProfile();
        // Test Case 6: Handle non-existing user ID
        //        testNonExistingUserId();
        // Test Case 7: Handle empty database
        //        testEmptyDatabase();
        // ########### Content test cases ###########
        // Test Case 1: Create a new post
        //          testCreatePost();
        // Test Case 2: Create a new story
        //        testCreateStory();
        // Test Case 3: Retrieve all content
        //        testGetAllContent();
        // Test Case 4: Update an existing post
        //        testUpdatePost();
        // Test Case 5: Delete a post by ID
        //        testDeletePost();
        // Test Case 6: Retrieve a post by ID
        //        testRetrievePostById();
        // Test Case 7: Retrieve a story by ID
        //        testRetrieveStoryById();
        // ContentController test cases
//        testGetAllContentsWithContents();
//        testGetAllContentsWithoutContents();
//        testGetAllPosts();
//        testGetAllStories();
//        testGetAllPostsWithoutPosts();
//        testGetAllStoriesWithoutStories();
//        testGetAllContentsWithInvalidUserId();
//        testGetAllPostsWithMixedContent();
// Test Case 8: Test expiration logic for story
//        testStoryExpiration();
// ########### Friend Management Test Cases ###########
//        FriendsManager friendsManager = new FriendsManager();
// Test Case 1: Send a friend request
//        testSendFriendRequest(friendsManager);
// Test Case 2: Accept a friend request
//        testAcceptFriendRequest(friendsManager);
// Test Case 3: Reject a friend request
//        testRejectFriendRequest(friendsManager);
// Test Case 4: Block a user
//        testBlockUser(friendsManager);
// Test Case 5: Get all friends
//        testGetFriends(friendsManager);
// Test Case 6: Get all friend requests
//        testGetFriendRequests(friendsManager);
// Test Case 7: Get all blocked users
//        testGetBlockedUsers(friendsManager);
//         Test Getting all friends of a user
//        testGetAllFriends();
//        testSuggestFriends();
//        BlockedMapper.getAll();
//        for(Blocked block: BlockedMapper.getAll())
//        {
//            System.out.println(block.getID());
//        }
//        for(Friend friend: FriendMapper.getAll())
//        {
//            System.out.println(friend.getID());
//        }
//        FriendMapper.getAll();
//        testDPEntityCreation();
//        CredentialsValidation validation = new CredentialsValidation("ibrahim", "111555333");
//        boolean isValid = validation.validate("");  // 'data' is not needed and can be passed as an empty string or placeholder
//        testUserGroups();

        // Client listening to the server
        
//        LongPollingServer server = new LongPollingServer();
//        Notifier notifier = new Notifier(server);
//        NotificationProducer producer = new NotificationProducer();
//        producer.addObserver(notifier);
//
//        NotificationService service = new NotificationService(producer);
//
//        // Simulate a client
//        NotificationClient client = new NotificationClient(server);
//        new Thread(client::listenForNotifications).start();

        // Simulate sending a notification
//        Notification notification = new Notification(4, "Chat", "You have a new message!");
//        service.sendNotification(notification);

//        testCreateGroup();
//        testPromoteToAdmin();
//        testDemoteFromAdmin();
//        testSendMembershipRequest();
//        testAcceptMembershipRequest();
//        testDeclineMembershipRequest();
//        testRemoveMember();
//        testDeleteGroup();
//        testAddPost();
//        testEditPost();
//        testRemovePost();

//            testMessaging();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ConnectHub.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

// ########### User test cases ###########  
    private static void testCreateUser() throws InvalidKeySpecException {
        System.out.println("Running Test Case 1: Create User");
        User newUser = new User("roborenger72@gmail.com", "ibrahim", "111555333", LocalDate.of(2003, 10, 26));
        UserMapper.create(newUser);
        System.out.println("User created successfully.");
    }

    private static void testGetAllUsers() {
        System.out.println("Running Test Case 2: Get All Users");
        List<User> usersList = UserMapper.getAll();
        if (usersList.isEmpty()) {
            System.out.println("No users found.");
        } else {
            usersList.forEach(user -> System.out.println("ID: " + user.getID() + " Name: " + user.getUsername()
                    + ", Email: " + user.getEmail() + " Password: " + user.getPassword() + " Date: " + user.getDateOfBirth()));
        }
    }

    private static void testDeleteUserById() {
        System.out.println("Running Test Case 3: Delete User By ID");
        UserMapper.delete(0);
        System.out.println("User deleted successfully.");
    }

    private static void testUpdateUser() throws InvalidKeySpecException {
        System.out.println("Running Test Case 4: Update User");
        User updatedUser = new User("roborenger72@gmail.com", "sha3boly", "12341231", LocalDate.of(2003, 10, 26));
        UserMapper.update(12, updatedUser);
        System.out.println("User updated successfully.");
    }

    private static void testRetrieveUserWithFilters() {
        System.out.println("Running Test Case 5: Retrieve User with Filters");

        Predicate<User> filterByEmail = user -> user.getEmail().equals("roborenger72@gmail.com");
        Predicate<User> filterByStatus = user -> user.getStatus().equals("offline");
        Predicate<User> filterById = user -> user.getID() == 15;

        List<Predicate<User>> filters = List.of(filterByEmail, filterByStatus, filterById);

        Optional<User> user = UserMapper.get(filters);
        user.ifPresentOrElse(
                u -> System.out.println("User found: " + u.getUsername()),
                () -> System.out.println("No user found with the given filters.")
        );
    }

    private static void testRetrieveUserById() {
        System.out.println("Running Test Case 6: Retrieve User by ID");
        Optional<User> userByID = UserMapper.get(15);
        userByID.ifPresentOrElse(
                u -> System.out.println("User found: " + u.getUsername()),
                () -> System.out.println("No user found with the given ID.")
        );
    }

    // ########### Profile test cases ###########
    private static void testCreateProfile() {
        System.out.println("Running Test Case 1: Create Profile");
        Profile profile = new Profile(1, "Hello, world!", "path/to/profile.jpg", "path/to/cover.jpg");
        ProfileMapper.create(profile);
        System.out.println("Profile created successfully.");
    }

    private static void testGetAllProfiles() {
        System.out.println("Running Test Case 2: Get All Profiles");
        var profiles = ProfileMapper.getAll();
        if (profiles.isEmpty()) {
            System.out.println("No profiles found.");
        } else {
            System.out.println("Retrieved profiles: " + profiles.size());
        }
    }

    private static void testGetProfileByUserId() {
        System.out.println("Running Test Case 3: Get Profile by User ID");
        Optional<Profile> profile = ProfileMapper.get(4);
        if (profile.isPresent()) {
            System.out.println("Profile found " + profile.get().getBio());
        } else {
            System.out.println("No profile found");
        }
    }

    private static void testUpdateProfile() {
        System.out.println("Running Test Case 4: Update Profile");
        Profile updatedProfile = new Profile(11, "Updated Bio", "new/path/profile.jpg", "new/path/cover.jpg");
        boolean isUpdated = ProfileMapper.update(2, updatedProfile);
        if (isUpdated) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile.");
        }
    }

    private static void testDeleteProfile() {
        System.out.println("Running Test Case 5: Delete Profile");
        boolean isDeleted = ProfileMapper.delete(1);
        if (isDeleted) {
            System.out.println("Profile deleted successfully.");
        } else {
            System.out.println("Failed to delete profile.");
        }
    }

    private static void testNonExistingUserId() {
        System.out.println("Running Test Case 6: Non-Existing User ID");
        Optional<Profile> profile = ProfileMapper.get(99);
        if (profile.isEmpty()) {
            System.out.println("Correctly handled non-existing User ID.");
        } else {
            System.out.println("Unexpected profile found: " + profile.get().getBio());
        }
    }

    private static void testEmptyDatabase() {
        System.out.println("Running Test Case 7: Empty Database");
        var profiles = ProfileMapper.getAll();
        if (profiles.isEmpty()) {
            System.out.println("Empty database handled correctly.");
        } else {
            System.out.println("Unexpected data found in empty database.");
        }
    }

    // ########### Content test cases ###########
    private static void testCreatePost() {
        System.out.println("Running Test Case 1: Create Post");
        Post newPost = new Post(1, "This is a test post.", null);
        ContentMapper.create(newPost);
        System.out.println("Post created successfully.");
    }

    private static void testCreateStory() {
        System.out.println("Running Test Case 2: Create Story");
        Story newStory = new Story(2, "This is a test story.", null);
        ContentMapper.create(newStory);
        System.out.println("Story created successfully.");
    }

    private static void testGetAllContent() {
        System.out.println("Running Test Case 3: Get All Content");
        List<Content> contentList = ContentMapper.getAll();
        if (contentList.isEmpty()) {
            System.out.println("No content found.");
        } else {
            contentList.forEach(content -> System.out.println("ID: " + content.getID() + " Content: " + content.getContent()));
        }
    }

    private static void testUpdatePost() {
        System.out.println("Running Test Case 4: Update Post");
        Post updatedPost = new Post(1, "Updated content for post.", null);
        ContentMapper.update(1, updatedPost);
        System.out.println("Post updated successfully.");
    }

    private static void testDeletePost() {
        System.out.println("Running Test Case 5: Delete Post");
        ContentMapper.delete(1);
        System.out.println("Post deleted successfully.");
    }

    private static void testRetrievePostById() {
        System.out.println("Running Test Case 6: Retrieve Post by ID");
        Optional<Content> contentOptional = ContentMapper.get(3); // This returns Optional<Content>

        contentOptional.ifPresentOrElse(
                content -> {
                    if (content instanceof Post) {
                        Post post = (Post) content;
                        System.out.println("Post found: " + post.getContent());
                    } else {
                        System.out.println("Found content is not a Post.");
                    }
                },
                () -> System.out.println("No content found with the given ID.")
        );
    }

    private static void testRetrieveStoryById() {
        System.out.println("Running Test Case 7: Retrieve Story by ID");
        Optional<Content> contentOptional = ContentMapper.get(2); // This returns Optional<Content>

        contentOptional.ifPresentOrElse(
                content -> {
                    if (content instanceof Story) {
                        Story story = (Story) content;
                        System.out.println("Story found: " + story.getContent());
                    } else {
                        System.out.println("Found content is not a Story.");
                    }
                },
                () -> System.out.println("No content found with the given ID.")
        );
    }

    private static void testStoryExpiration() {
        System.out.println("Running Test Case 8: Test Story Expiration");
//        Story story = new Story(3, "Story that should expire.");
//        story.setTimestamp(LocalDateTime.now().minus(25, ChronoUnit.HOURS));  // Set story's timestamp to 25 hours ago

        Optional<Content> contentOptional = ContentMapper.get(1); // This returns Optional<Content>

        contentOptional.ifPresentOrElse(
                content -> {
                    if (content instanceof Story) {
                        if (content.isExpired()) {
                            System.out.println("Story expired as expected.");
                        } else {
                            System.out.println("Story did not expire as expected.");
                        }
                    } else {
                        System.out.println("Found content is not a Story.");
                    }
                },
                () -> System.out.println("No content found with the given ID.")
        );
    }

    public static void testGetAllContentsWithContents() {
        int userId = 1;
        List<Content> contents = ContentController.getAllContents(userId);
        System.out.println("Contents for user " + userId);
        for (Content content : contents) {
            System.out.println(content);
        }
        // Expected: List of all contents authored by userId
    }

    public static void testGetAllContentsWithoutContents() {
        int userId = 99; // Assume this user has no content
        List<Content> contents = ContentController.getAllContents(userId);
        System.out.println("Contents for user " + userId + ": " + contents);
        // Expected: Empty list []
    }

    public static void testGetAllPosts() {
        int userId = 30;
        List<Content> posts = ContentController.getAllPosts(userId);
        System.out.println("Contents for user " + userId);
        for (Content content : posts) {
            System.out.println(content);
        }
        // Expected: List of posts authored by userId
    }

    public static void testGetAllStories() {
        int userId = 30;
        List<Content> stories = ContentController.getAllStories(userId);
        System.out.println("Stories for user " + userId);
        for (Content content : stories) {
            System.out.println(content);
        }
        // Expected: List of stories authored by userId
    }

    public static void testGetAllPostsWithoutPosts() {
        int userId = 99; // Assume this user has no posts
        List<Content> posts = ContentController.getAllPosts(userId);
        System.out.println("Posts for user " + userId + ": " + posts);
        // Expected: Empty list []
    }

    public static void testGetAllStoriesWithoutStories() {
        int userId = 99; // Assume this user has no stories
        List<Content> stories = ContentController.getAllStories(userId);
        System.out.println("Stories for user " + userId + ": " + stories);
        // Expected: Empty list []
    }

    public static void testGetAllContentsWithInvalidUserId() {
        int userId = -1; // Invalid user ID
        List<Content> contents = ContentController.getAllContents(userId);
        System.out.println("Contents for invalid user ID " + userId + ": " + contents);
        // Expected: Empty list []
    }

    public static void testGetAllPostsWithMixedContent() {
        int userId = 2; // Assume this user has posts and stories
        List<Content> posts = ContentController.getAllPosts(userId);
        System.out.println("Posts for user " + userId + ": " + posts);
        // Expected: List containing only posts authored by userId
    }

    // ########### Friends Management test cases ###########
    // Test Case 1: Send a friend request
    public static void testSendFriendRequest(FriendsManager friendsManager) {
        System.out.println("Test Case 1: Send a friend request");
        friendsManager.sendFriendRequest(4, 5);  // Sender ID: 1, Receiver ID: 2
        System.out.println("Friend request sent from User 1 to User 2");
    }

    // Test Case 2: Accept a friend request
    public static void testAcceptFriendRequest(FriendsManager friendsManager) {
        System.out.println("Test Case 2: Accept a friend request");

        // Assuming that a friend request from User 1 to User 2 exists in the list
        FriendRequest request = friendsManager.getFriendRequests().get(0); // Get first request
        friendsManager.acceptFriendRequest(request);
        System.out.println("Friend request from User 1 to User 2 accepted");
    }

    // Test Case 3: Reject a friend request
    public static void testRejectFriendRequest(FriendsManager friendsManager) {
        System.out.println("Test Case 3: Reject a friend request");

        // Assuming a second friend request exists
        FriendRequest request = friendsManager.getFriendRequests().get(0); // Get second request
        friendsManager.rejectFriendRequest(request);
        System.out.println("Friend request from User 2 to User 3 rejected");
    }

    // Test Case 4: Block a user
    public static void testBlockUser(FriendsManager friendsManager) {
        System.out.println("Test Case 4: Block a user");

        // Block User 2 by User 1
        friendsManager.blockUser(6, 9);
        System.out.println("User X blocked User Y");

        // Block User 3 by User 1 (testing prevention of duplicate blocks)
//        friendsManager.blockUser(9, 13);
//        System.out.println("User 1 blocked User 3");
        // Block User 2 by User 1 again (should prevent duplicate block)
//        friendsManager.blockUser(9, 13);
//        System.out.println("Attempt to block User 2 again (duplicate block prevented)");
    }

    // Test Case 5: Get all friends
    public static void testGetFriends(FriendsManager friendsManager) {
        System.out.println("Test Case 5: Get all friends");
        friendsManager.getFriends().forEach(friend -> System.out.println("Friend: User " + friend.getUserId() + " and User " + friend.getFriendId()));
    }

    // Test Case 6: Get all friend requests
    public static void testGetFriendRequests(FriendsManager friendsManager) {
        System.out.println("Test Case 6: Get all friend requests");
        friendsManager.getFriendRequests().forEach(request -> System.out.println("Friend Request: " + request.getSenderId() + " to " + request.getReceiverId()));
    }

    // Test Case 7: Get all blocked users
    public static void testGetBlockedUsers(FriendsManager friendsManager) {
        System.out.println("Test Case 7: Get all blocked users");
        friendsManager.getBlocks().forEach(block -> System.out.println("Blocked: User " + block.getUserId() + " blocked User " + block.getBlockedId()));
    }

    public static void testGetAllFriends() {
        // Assume user with ID 1 has friends in the database.
        int userId = 1;

        // Act
        List<User> friends = FriendController.getAllFriends(userId);
        for (User friend : friends) {
            System.out.println(friend.getID());
        }
    }

    public static void testSuggestFriends() {
        // Assume user with ID 1 has friends in the database.
        int userId = 6;

        // Act
        List<User> suggestedUsers = FriendController.suggestFriends(userId);
        for (User suggestedUser : suggestedUsers) {
            System.out.println(suggestedUser.getID());
        }
    }

    // ########### DPs test cases ###########
    public static void testDPEntityCreation() {
        System.out.println("Test Case 1: constructing a user using factory");
        UserBuilder userBuilder = UserBuilder.getInstance()
                .setEmail("user@example.com")
                .setUsername("john_doe")
                .setPassword("securePassword")
                .setDateOfBirth(LocalDate.of(1990, 1, 1));
        User newUser = (User) Factory.createEntity(userBuilder.getInstance());
        System.out.println(newUser);
        //////////////////////////////////////////////////////////////////////////
        System.out.println("Test Case 2: constructing a post using factory");
        ContentBuilder contentBuilder = ContentBuilder.getInstance()
                .setContent("zrbew lnew")
                .setAuthorId(30)
                .setContentType(ContentType.POST);
        Content newPost = (Post) Factory.createEntity(contentBuilder.getInstance());
        System.out.println(newPost);
        System.out.println("Test Case 3: constructing a story using factory");
        contentBuilder
                .setContent("zrbew lnew")
                .setAuthorId(30)
                .setContentType(ContentType.STORY);
        Content newStory = (Story) Factory.createEntity(contentBuilder.getInstance());
        System.out.println(newStory);
    }

    private void assertFalse(boolean empty) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertTrue(boolean anyMatch) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    private static void testMessaging() throws InterruptedException {
//        ChatWatcher chatWatcher = new ChatWatcher();
//
//        chatWatcher.startWatching();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(2000);
//                Message newMessage = new Message(1, 2, 1, "test");
//                MessageMapper.create(newMessage);
//                Thread.sleep(2000);
//                Message newMessage2 = new Message(1, 2, 1, "test2");
//                MessageMapper.create(newMessage2);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }).start();
//
//        // Allow some time to fetch new messages
//        Thread.sleep(7000);
//
//        // Stop watching
//        chatWatcher.stopWatching();
//
//        // Fetch the final batch of new messages
//        List<Message> finalMessages = chatWatcher.getNewMessages();
//        System.out.println("Final fetched messages: " + finalMessages);
//    }
    
    // Test Case 1: Test Promote User to Admin
    public static void testPromoteToAdmin() {
        // Test data setup (groupID, userID, callerID)
        int groupID = 1;
        int userID = 5;
        int callerID = 2;  // Creator's ID
        GroupAuthorityManager.promoteToAdmin(groupID, userID, callerID);
    }

    // Test Case 2: Test Demote User from Admin
    public static void testDemoteFromAdmin() {
        // Test data setup (groupID, userID, callerID)
        int groupID = 1;
        int userID = 5;
        int callerID = 2;  // Creator's ID
        GroupAuthorityManager.demoteFromAdmin(groupID, userID, callerID);
    }

    // Test Case 3: Test Send Membership Request
    public static void testSendMembershipRequest() {
        // Test data setup (groupID, userID)
        int groupID = 1;
        int userID = 2;
        GroupAuthorityManager.sendMembershipRequest(groupID, userID);
    }

    // Test Case 4: Test Accept Membership Request
    public static void testAcceptMembershipRequest() {
        // Test data setup (MembershipRequest request, callerID)
        MembershipRequest request = new MembershipRequest(1, 8);  // groupID=1, userID=2
        int callerID = 5;  // Admin or Creator ID
        GroupAuthorityManager.acceptMembershipRequest(request, callerID);
    }

    // Test Case 5: Test Decline Membership Request
    public static void testDeclineMembershipRequest() {
        // Test data setup (MembershipRequest request, callerID)
        MembershipRequest request = new MembershipRequest(1, 8);  // groupID=1, userID=2
        int callerID = 2;  // Admin or Creator ID
        GroupAuthorityManager.declineMembershipRequest(request, callerID);
    }

    // Test Case 6: Test Remove Member
    public static void testRemoveMember() {
        // Test data setup (groupID, userID, callerID)
        int groupID = 1;
        int userID = 5;
        int callerID = 2;  // Admin or Creator's ID
        GroupAuthorityManager.removeMember(groupID, userID, callerID);
    }

    // Test Case 7: Test Delete Group
    public static void testDeleteGroup() {
        // Test data setup (groupID, callerID)
        int groupID = 1;
        int callerID = 2;  // Creator's ID
        GroupAuthorityManager.deleteGroup(groupID, callerID);
    }
    
    public static void testAddPost()
    {
        int authorID = 6;
        int groupID = 2;
        int callerID = 6;
        PostGroup postGroup = new PostGroup(authorID, groupID, "This is a post", "");
        GroupAuthorityManager.addPost(postGroup, callerID);
    }
    
    public static void testEditPost()
    {
        int callerID = 6;
        PostGroup postGroup = PostGroupMapper.get(2).get();
        postGroup.setContent("This is an updated post");
        GroupAuthorityManager.editPost(postGroup, callerID);
    }
    
    public static void testRemovePost()
    {
        int callerID = 4;
        PostGroup postGroup = PostGroupMapper.get(1).get();
        GroupAuthorityManager.deletePost(postGroup, callerID);
    }
    
    public static void testCreateGroup()
    {
        Group group = new Group(5, "zerbewelnew", "zerbewzzz");
        GroupMapper.create(group);
    }
}
