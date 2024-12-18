/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.entities.Content;
import connecthub.entities.Post;
import connecthub.entities.PostGroup;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ShowGroupPost extends javax.swing.JFrame {

    Post post;
    GroupPosts groupPosts;
    Newsfeed newsFeed;
    public ShowGroupPost(Post post,GroupPosts groupPosts) {
        if (post == null) {
            JOptionPane.showMessageDialog(this, "No content to display.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.post = post;
        this.groupPosts=groupPosts;
        this.newsFeed = null;
        initComponents();
        initializeContent();
//        System.out.println("ana 5alst");
//        System.out.println(this.x.getImagePath());
//        System.out.println(this.x.getContent());
    }
    
    public ShowGroupPost(Post post,Newsfeed newsFeed) {
        if (post == null) {
            JOptionPane.showMessageDialog(this, "No content to display.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.post = post;
        this.groupPosts=null;
        this.newsFeed = newsFeed;
        initComponents();
        initializeContent();
//        System.out.println("ana 5alst");
//        System.out.println(this.x.getImagePath());
//        System.out.println(this.x.getContent());
    }

    private void initializeContent() {
        // Set the text content
        content.setText("<html><p style='width: 400px;'>" + post.getContent() + "</p></html>"); // Wrap text
        content.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        content.setPreferredSize(new Dimension(400, 120)); // Explicit size for content label

        // Set the image
        if (post.getImagePath() != null && !post.getImagePath().isEmpty()) {
            try {
                ImageIcon originalIcon = new ImageIcon(post.getImagePath());
                Image img = originalIcon.getImage();

                // Resize the image to fit the label
                Image scaledImg = img.getScaledInstance(173, 154, Image.SCALE_SMOOTH); // Use fixed dimensions
                photo.setIcon(new ImageIcon(scaledImg));
            } catch (Exception e) {
                System.out.println("ana gebtk hena");
                photo.setText("Image not found.");
                e.printStackTrace();
            }
        } else {
            photo.setText("No image available.");
        }

        // Set preferred size for photo
        photo.setPreferredSize(new Dimension(173, 154));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JLabel();
        photo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Show Post");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (post == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
          if(newsFeed == null)
          {
              groupPosts.setVisible(true);
              groupPosts.setLocationRelativeTo(null);
              setVisible(false);
          } else if (groupPosts == null) {
              newsFeed.setVisible(true);
              newsFeed.setLocationRelativeTo(null);
              setVisible(false);
          }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel content;
    private javax.swing.JLabel photo;
    // End of variables declaration//GEN-END:variables
}
