package Frontend;

import connecthub.GroupAuthorityManager;
import connecthub.entities.Group;
import connecthub.entities.Post;
import connecthub.entities.PostGroup;
import connecthub.entities.User;
import connecthub.mappers.ContentMapper;
import connecthub.mappers.PostGroupMapper;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AddGroupPost extends javax.swing.JFrame {

    Group group;
    User user;
    GroupPosts groupPosts;
    
    public AddGroupPost(Group group, GroupPosts groupPosts,User user) {
        if (group == null || groupPosts == null) {
            throw new IllegalArgumentException("User and Newsfeed cannot be null");
        }
        initComponents();
        this.group = group;
        this.groupPosts = groupPosts;
        this.user=user;
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JTextArea();
        photo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        post = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add post");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("New Post");

        content.setColumns(20);
        content.setRows(5);
        content.setText("\n\n\n\n\n\n");
        jScrollPane1.setViewportView(content);

        photo.setBackground(new java.awt.Color(0, 102, 153));
        photo.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Write content:");

        post.setBackground(new java.awt.Color(0, 51, 102));
        post.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        post.setForeground(new java.awt.Color(255, 255, 255));
        post.setText("Post");
        post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(post)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postActionPerformed

        try {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);

            File selectedFile = null;
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }

            if ((selectedFile == null || !selectedFile.exists()) && content.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Both fields are empty! Please provide text content or an image.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (selectedFile != null && selectedFile.exists()) {
                ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
                Image img = originalIcon.getImage();

                Image scaledImg = img.getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                photo.setIcon(scaledIcon);
            }

            PostGroup postGroup = new PostGroup(user.getID(),group.getID(), content.getText().trim(),
                    selectedFile != null ? selectedFile.getAbsolutePath() : null);
            GroupAuthorityManager.addPost(postGroup, user.getID());

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Post created successfully!",
                    "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while creating the post. Please try again.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }

    }//GEN-LAST:event_postActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         if (group == null || user == null|| groupPosts==null) {
            JOptionPane.showMessageDialog(this,
                    "User or groupPosts or group data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        groupPosts.setVisible(true);
       groupPosts.setLocationRelativeTo(null); // Center the window
       groupPosts.fillList();
       setVisible(false);

    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel photo;
    private javax.swing.JToggleButton post;
    // End of variables declaration//GEN-END:variables
}
