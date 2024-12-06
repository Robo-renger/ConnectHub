/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.controllers.ContentController;
import connecthub.controllers.FriendController;
import connecthub.entities.Content;
import connecthub.entities.User;
import connecthub.mappers.ContentMapper;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;

/**
 *
 * @author Mahinour Mohamed
 */
public class Newsfeed extends javax.swing.JFrame {

    User u;
    List<Content> allStories;
    List<Content> allPosts;

    /**
     * Creates new form Newsfeed
     */
    public Newsfeed(User u) {
        initComponents();
        this.u = u;
        FillPostList();
        FillStoryList();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(post)
                        .addGap(56, 56, 56)
                        .addComponent(story, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(refresh)
                        .addGap(50, 50, 50)
                        .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        FrontProfile f = FrontProfile.getInstanceOf();
        f.setVisible(true);
        f.setLocation(null);
        setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postActionPerformed
        AddPost p = new AddPost(u,this);
        p.setVisible(true);
        p.setLocation(null);
        setVisible(false);

    }//GEN-LAST:event_postActionPerformed

    private void storyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storyActionPerformed
        AddStory s = new AddStory(u,this);
        s.setVisible(true);
        s.setLocation(null);
        setVisible(false);

    }//GEN-LAST:event_storyActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        FillPostList();
        FillStoryList();

    }//GEN-LAST:event_refreshActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        try {
            int i = Posts.getSelectedIndex();
            int j = Stories.getSelectedIndex();
            if (j > 0) {
                ShowContent s = new ShowContent(allStories.get(j));

            } else if (i > 0) {

                ShowContent s = new ShowContent(allPosts.get(i));
            }

        } catch (Exception e) {
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
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JToggleButton post;
    private javax.swing.JToggleButton refresh;
    private javax.swing.JToggleButton story;
    private javax.swing.JToggleButton view;
    // End of variables declaration//GEN-END:variables

}
