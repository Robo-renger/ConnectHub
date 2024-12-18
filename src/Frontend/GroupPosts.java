/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.DataBaseManager;
import connecthub.GroupAuthorityManager;
import connecthub.NotificationManager;
import connecthub.controllers.GroupController;
import connecthub.entities.Comment;
import connecthub.entities.Group;
import connecthub.entities.Post;
import connecthub.mappers.CommentMapper;
import connecthub.entities.PostGroup;
import connecthub.entities.User;
import connecthub.mappers.ContentMapper;
import connecthub.mappers.PostGroupMapper;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahinour Mohamed
 */
public class GroupPosts extends javax.swing.JFrame {

    /**
     * Creates new form GroupPosts
     */
    Group group;
    User user;
    List<Post> posts;
    GroupProfile groupProfile;

    public GroupPosts(Group group, User user, GroupProfile groupProfile) {
        initComponents();
        this.group = group;
        this.user = user;
        this.groupProfile = groupProfile;
        fillList();
        initListSelectionListener();
    }

    public void fillList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        posts = PostGroupMapper.getAllPostsByGroupID(group.getID());
        for (Post post : posts) {
            listModel.addElement(post.getContent());
        }

        postsList.setModel(listModel);
        if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Member")) {
            deletePost.setVisible(false);
            editPost.setVisible(false);
        }

    }

    private void onListSelectionChanged(javax.swing.event.ListSelectionEvent evt) {
        int selectedValue = postsList.getSelectedIndex();

        if (selectedValue >= 0) {
            Post selectedPost = posts.get(selectedValue);

            boolean isMyPost = (selectedPost.getAuthorId() == user.getID()
                    || group.getCreatorID() == user.getID()
                    || GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Admin"));

            editPost.setVisible(false);
            deletePost.setVisible(false);

            if (isMyPost) {
                editPost.setVisible(true);
                deletePost.setVisible(true);
            }
        }
    }

    private void initListSelectionListener() {
        postsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onListSelectionChanged(evt);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        postsList = new javax.swing.JList<>();
        createPost = new javax.swing.JToggleButton();
        editPost = new javax.swing.JToggleButton();
        deletePost = new javax.swing.JToggleButton();
        view = new javax.swing.JToggleButton();
        comments = new javax.swing.JToggleButton();
        like = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Group Posts");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 51, 102));
        label1.setText("              Posts");

        jScrollPane1.setViewportView(postsList);

        createPost.setBackground(new java.awt.Color(0, 51, 102));
        createPost.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        createPost.setForeground(new java.awt.Color(255, 255, 255));
        createPost.setText("Create new post");
        createPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPostActionPerformed(evt);
            }
        });

        editPost.setBackground(new java.awt.Color(0, 51, 102));
        editPost.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        editPost.setForeground(new java.awt.Color(255, 255, 255));
        editPost.setText("Edit post");
        editPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPostActionPerformed(evt);
            }
        });

        deletePost.setBackground(new java.awt.Color(0, 51, 102));
        deletePost.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        deletePost.setForeground(new java.awt.Color(255, 255, 255));
        deletePost.setText("Delete post");
        deletePost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePostActionPerformed(evt);
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

        comments.setBackground(new java.awt.Color(0, 51, 102));
        comments.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        comments.setForeground(new java.awt.Color(255, 255, 255));
        comments.setText("Comments");
        comments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentsActionPerformed(evt);
            }
        });

        like.setBackground(new java.awt.Color(0, 51, 102));
        like.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        like.setForeground(new java.awt.Color(255, 255, 255));
        like.setText("Like");
        like.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                likeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(deletePost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(createPost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comments, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 74, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editPost, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(like, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createPost)
                    .addComponent(editPost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletePost)
                    .addComponent(view))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comments)
                    .addComponent(like))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPostActionPerformed
        if (user == null || group == null) {
            JOptionPane.showMessageDialog(this,
                    "User or profile data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        AddGroupPost addPostWindow = new AddGroupPost(group, this, user);
        addPostWindow.setVisible(true);
        addPostWindow.setLocationRelativeTo(null); // Center the window
        setVisible(false);
        fillList();
    }//GEN-LAST:event_createPostActionPerformed

    private void editPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPostActionPerformed
        if (user == null || group == null) {
            JOptionPane.showMessageDialog(this,
                    "User or profile data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = postsList.getSelectedIndex();

            if (index >= 0) {
                EditPost editPost = new EditPost(posts.get(postsList.getSelectedIndex()), this, user, group);
                editPost.setVisible(true);
                editPost.setLocationRelativeTo(null); // Center the window
                setVisible(false);
                fillList();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_editPostActionPerformed

    private void deletePostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePostActionPerformed
        if (user == null || group == null) {
            JOptionPane.showMessageDialog(this,
                    "User or profile data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = postsList.getSelectedIndex();
            if (posts.get(index).getAuthorId() == user.getID()) {
                deletePost.setVisible(true);
            }
            if (index >= 0) {
                GroupAuthorityManager.deletePost(posts.get(index), group.getID(),user.getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Deleted successfully!", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                fillList();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_deletePostActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        if (user == null || group == null) {
            JOptionPane.showMessageDialog(this,
                    "User or profile data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = postsList.getSelectedIndex();
            if (index >= 0) {
                ShowGroupPost showGroupPost = new ShowGroupPost(posts.get(index), this);
                showGroupPost.setVisible(true);
                showGroupPost.setLocationRelativeTo(null); // Center the window
                setVisible(false);
                fillList();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_viewActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (user == null || group == null || groupProfile == null) {
            JOptionPane.showMessageDialog(this,
                    "User or GroupProfile or group data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            groupProfile.setVisible(true);
            groupProfile.setLocationRelativeTo(null);
            setVisible(false);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_formWindowClosing

    private void commentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentsActionPerformed
        // TODO add your handling code here:
        if (user == null || group == null || groupProfile == null) {
            JOptionPane.showMessageDialog(this,
                    "User or GroupProfile or group data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int selectedValue = postsList.getSelectedIndex();

            if (selectedValue >= 0) {
                Post selectedPost = posts.get(selectedValue);
//                List<Comment> postComments = CommentMapper.getPostComments(selectedPost.getID());
                Comments commentsPage = new Comments(selectedPost);
                commentsPage.setLocationRelativeTo(null);
                commentsPage.setVisible(true);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_commentsActionPerformed

    private void likeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_likeActionPerformed
        // TODO add your handling code here:
        if (user == null || group == null || groupProfile == null) {
            JOptionPane.showMessageDialog(this,
                    "User or GroupProfile or group data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int selectedValue = postsList.getSelectedIndex();
            if (selectedValue >= 0) {
                Post selectedPost = posts.get(selectedValue);
                if (liked == false) {
                    selectedPost.like();
                    liked = true;
                    NotificationManager notificationManager = new NotificationManager();
                    notificationManager.sendNotification(selectedPost.getAuthorId(), "Like", "You have a new like on your post " + selectedPost.getAuthorId());
                    JOptionPane.showMessageDialog(this,
                            "You have liked this post!",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "You have ALREADY liked this post!",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_likeActionPerformed

    /**
     * @param args the command line arguments
     */
    private boolean liked = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton comments;
    private javax.swing.JToggleButton createPost;
    private javax.swing.JToggleButton deletePost;
    private javax.swing.JToggleButton editPost;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JToggleButton like;
    private javax.swing.JList<String> postsList;
    private javax.swing.JToggleButton view;
    // End of variables declaration//GEN-END:variables
}
