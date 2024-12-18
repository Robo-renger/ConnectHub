/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.NotificationManager;
import connecthub.NotificationService;
import connecthub.controllers.ContentController;
import connecthub.controllers.FriendController;
import connecthub.entities.Content;
import connecthub.entities.Notification;
import connecthub.entities.Profile;
import connecthub.entities.User;
import connecthub.interfaces.Observer;
import connecthub.mappers.ContentMapper;
import connecthub.mappers.GroupMapper;
import connecthub.mappers.NotificationMapper;
import connecthub.mappers.ProfileMapper;
import connecthub.mappers.UserMapper;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mahinour Mohamed
 */
public class Newsfeed extends javax.swing.JFrame implements Observer {

    User u;
    Profile p;
    List<Content> allStories;
    List<Content> allPosts;
    List<Notification> notificationList;

    NotificationManager notificationManager = new NotificationManager();
    NotificationService notificationService = new NotificationService(notificationManager);

    /**
     * Creates new form Newsfeed
     */
    public Newsfeed(User u, Profile p) {
        if (u == null || p == null) {
            throw new IllegalArgumentException("User and Profile cannot be null");
        }
        initComponents();
        Optional<User> logged = UserMapper.getLoggedInUser();
        if (!logged.isEmpty()) {
            this.u = logged.get();
        }
        for (Profile profile : ProfileMapper.getAll()) {
            if (profile.getUserID() == this.u.getID()) {
                this.p = profile;
            }
        }

        notificationList = NotificationMapper.getAllForRecipient(u.getID());
        loadNotifications();
        notificationManager.addObserver(this);
        notificationService.start();

//        if (this.u == null || this.p == null) {
//            throw new IllegalArgumentException("User and Profile cannot be null");
//        }
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Refetch notifications and reload them
                notificationList = NotificationMapper.getAllForRecipient(u.getID());
                loadNotifications(); // Reload notifications
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Do nothing when the window loses focus
            }
        });
        FillPostList();
        FillStoryList();
    }

    private void loadNotifications() {
        // Fetch all notifications from the database

        // Display the notifications in the notifications list
        DefaultListModel<String> notificationListModel = new DefaultListModel<>();

        for (Notification notification : notificationList) {
            notificationListModel.addElement(notification.getMessage());
            notification.setRead(true);
            NotificationMapper.update(notification.getID(), notification);
        }

        notifications.setModel(notificationListModel); // Update the JList in the UI
    }

    @Override
    public void update(Notification notification) {
        // This method will be called when a new notification arrives
        SwingUtilities.invokeLater(() -> {
            if (notification.getRecepientID() == this.u.getID()) {
                DefaultListModel<String> model = (DefaultListModel<String>) notifications.getModel();
                model.addElement(notification.getMessage()); // Add new notification to the UI list
                notification.setRead(true);
                NotificationMapper.update(notification.getID(), notification);
            }
        });
    }

    private void FillPostList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        allPosts = ContentController.getAllPosts(u.getID());
        for (Content content : allPosts) {
            listModel.addElement(content.getContent());
        }
        List<User> user = FriendController.getAllFriends(u.getID());
        for (User user1 : user) {
            List<Content> x = ContentController.getAllPosts(user1.getID());
            for (Content content : x) {
                listModel.addElement(content.getContent());
                allPosts.add(content);
            }
        }
        Posts.setModel(listModel);

    }

    private void FillStoryList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        allStories = ContentController.getAllStories(u.getID());
        for (Content content : allStories) {
            listModel.addElement(content.getContent());
        }
        List<User> user = FriendController.getAllFriends(u.getID());
        for (User user1 : user) {
            List<Content> x = ContentController.getAllStories(user1.getID());
            for (Content content : x) {
                listModel.addElement(content.getContent());
                allStories.add(content);
            }
        }
        Stories.setModel(listModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refresh = new javax.swing.JToggleButton();
        story = new javax.swing.JToggleButton();
        post = new javax.swing.JToggleButton();
        view = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Posts = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        Stories = new javax.swing.JList<>();
        label = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        notifications = new javax.swing.JList<>();
        label2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Newsfeed");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        refresh.setBackground(new java.awt.Color(0, 51, 102));
        refresh.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        story.setBackground(new java.awt.Color(0, 51, 102));
        story.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        story.setForeground(new java.awt.Color(255, 255, 255));
        story.setText("Add story");
        story.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storyActionPerformed(evt);
            }
        });

        post.setBackground(new java.awt.Color(0, 51, 102));
        post.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        post.setForeground(new java.awt.Color(255, 255, 255));
        post.setText("Create new post");
        post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postActionPerformed(evt);
            }
        });

        view.setBackground(new java.awt.Color(0, 51, 102));
        view.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        view.setForeground(new java.awt.Color(255, 255, 255));
        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(Posts);

        jScrollPane2.setViewportView(Stories);

        label.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        label.setForeground(new java.awt.Color(0, 51, 102));
        label.setText("            Stories");

        label1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 51, 102));
        label1.setText("              Posts");

        jScrollPane3.setViewportView(notifications);

        label2.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 51, 102));
        label2.setText("Notifications");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(post))
                                .addGap(56, 56, 56)
                                .addComponent(story, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(refresh)
                                .addGap(50, 50, 50)
                                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(label2)
                        .addGap(74, 74, 74))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refresh)
                    .addComponent(view)
                    .addComponent(story)
                    .addComponent(post))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (u == null || p == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (notificationService != null && notificationService.isAlive()) {
                notificationService.interrupt();
            }

            FrontProfile f = FrontProfile.getInstanceOf();
            f.handleButtonsTrue();
            f.setVisible(true);
            f.setLocation(null);
            setVisible(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_formWindowClosing

    private void postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postActionPerformed
        if (u == null || p == null) {
            JOptionPane.showMessageDialog(this,
                    "User or profile data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        AddPost addPostWindow = new AddPost(u, this);
        addPostWindow.setVisible(true);
        addPostWindow.setLocationRelativeTo(null); // Center the window
        setVisible(false);

    }//GEN-LAST:event_postActionPerformed

    private void storyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storyActionPerformed
        if (u == null || p == null) {
            JOptionPane.showMessageDialog(this,
                    "User or profile data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            AddStory s = new AddStory(u, this);
            s.setVisible(true);
            s.setLocationRelativeTo(null); // Center the window
            setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "An error occurred while opening AddStory: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_storyActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        FillPostList();
        FillStoryList();

    }//GEN-LAST:event_refreshActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        try {
            int i = Posts.getSelectedIndex(); // Get the selected index of Posts
            int j = Stories.getSelectedIndex(); // Get the selected index of Stories
            int n = notifications.getSelectedIndex();

            // Check if a story is selected
            if (j >= 0) {
                // Ensure a valid story is selected
                ShowContent s = new ShowContent(allStories.get(j), this);
                s.setVisible(true); // Show the content
                s.setLocationRelativeTo(null); // Center the window
                setVisible(false); // Hide the current window

            } else if (i >= 0) {
                // Check if a post is selected
                ShowContent s = new ShowContent(allPosts.get(i), this);
                s.setVisible(true); // Show the content
                s.setLocationRelativeTo(null); // Center the window
                setVisible(false); // Hide the current window
            } else if (n >= 0) {
                System.out.println(notificationList);
                // Check if a post is selected
                Notification selectedNotification = notificationList.get(n);
                if (selectedNotification.getNotificationType().equalsIgnoreCase("Friend request accepted")) {
                    FriendsList child = new FriendsList(u, this);
                    child.setVisible(true);
                    child.setLocationRelativeTo(null);
                    setVisible(false);
                } else if (selectedNotification.getNotificationType().equalsIgnoreCase("FriendRequest")) {
                    FriendsRequest friendsRequest = new FriendsRequest(u, this);
                    friendsRequest.setVisible(true);
                    friendsRequest.setLocationRelativeTo(null);
                    setVisible(false);
                } else if (selectedNotification.getNotificationType().equalsIgnoreCase("Group")) {
                    String message = selectedNotification.getMessage();
                    int lastSpaceIndex = message.lastIndexOf(" ");
                    int groupID = -1;

                    if (lastSpaceIndex != -1) {
                        String groupIdString = message.substring(lastSpaceIndex + 1);
                        try {
                            // Try to parse the groupID as an integer
                            groupID = Integer.parseInt(groupIdString);
                            System.out.println("Extracted groupID: " + groupID);
                        } catch (NumberFormatException e) {
                            System.err.println("Failed to parse groupID: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Message format is incorrect.");
                    }
                    GroupProfile groupProfile = new GroupProfile(GroupMapper.get(groupID).get(), u, this);
                    groupProfile.setVisible(true);
                    groupProfile.setLocationRelativeTo(null);
                    setVisible(false);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Posts;
    private javax.swing.JList<String> Stories;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JList<String> notifications;
    private javax.swing.JToggleButton post;
    private javax.swing.JToggleButton refresh;
    private javax.swing.JToggleButton story;
    private javax.swing.JToggleButton view;
    // End of variables declaration//GEN-END:variables

}
