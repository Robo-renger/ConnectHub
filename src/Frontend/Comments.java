/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.controllers.ContentController;
import connecthub.controllers.GroupController;
import connecthub.entities.Comment;
import connecthub.entities.Post;
import connecthub.entities.PostGroup;
import connecthub.entities.User;
import connecthub.mappers.CommentMapper;
import connecthub.mappers.UserMapper;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;

/**
 *
 * @author Mahinour Mohamed
 */
public class Comments extends javax.swing.JFrame {

    /**
     * Creates new form Comments
     */
    public Comments(Post post) {
        this.post = post;
        Optional<User> optUser = UserMapper.getLoggedInUser();
        if (optUser.isPresent()) {
            this.user = optUser.get();
        }
        initComponents();
        fillCommentsList();
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                fillCommentsList(); // Refresh the comments when window gains focus
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Do nothing when focus is lost
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        comments = new javax.swing.JList<>();
        addComment = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        commentsCounter = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        likesCounter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comments");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 51, 102));
        label1.setText("        Comments");

        jScrollPane1.setViewportView(comments);

        addComment.setBackground(new java.awt.Color(0, 51, 102));
        addComment.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        addComment.setForeground(new java.awt.Color(255, 255, 255));
        addComment.setText("Add Comment");
        addComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCommentActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Comments:");

        commentsCounter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Likes:");

        likesCounter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(addComment, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(likesCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(commentsCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(commentsCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(likesCounter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addComment, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCommentActionPerformed
        // TODO add your handling code here:
        try {
            AddComment AddCommentsPage = new AddComment(this.post);
             AddCommentsPage.setVisible(true);
            AddCommentsPage.setLocationRelativeTo(null);
            setVisible(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_addCommentActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing
    private void fillCommentsList() {
        try {
            // Retrieve comments for the post
            List<Comment> postComments = CommentMapper.getPostComments(this.post.getID());

            // Create a DefaultListModel to populate the JList
            DefaultListModel<String> listModel = new DefaultListModel<>();

            // Convert comments to strings and add to the model
            for (Comment comment : postComments) {
                listModel.addElement(comment.getContent());
            }

            comments.setModel(listModel);
            this.post.setCommentsCount(postComments.size());
            commentsCounter.setText(String.valueOf(this.post.getCommentsCount()));
            likesCounter.setText(String.valueOf(this.post.getLikesCount()));

        } catch (Exception e) {
            System.out.println("Error populating comments: " + e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    private Post post;
    private User user;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addComment;
    private javax.swing.JList<String> comments;
    private javax.swing.JLabel commentsCounter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel likesCounter;
    // End of variables declaration//GEN-END:variables
}
