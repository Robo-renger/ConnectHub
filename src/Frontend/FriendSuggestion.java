/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.FriendsManager;
import connecthub.controllers.FriendController;
import connecthub.entities.Content;
import connecthub.entities.Friend;
import connecthub.entities.FriendRequest;
import connecthub.entities.User;
import connecthub.mappers.ContentMapper;
import connecthub.mappers.FriendRequestMapper;
import connecthub.mappers.UserMapper;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahinour Mohamed
 */
public class FriendSuggestion extends javax.swing.JFrame {

    User u;
    FriendsManagement f;
    List<User> friends;

    /**
     * Creates new form FriendSuggestion
     */
    public FriendSuggestion(User u, FriendsManagement f) {
        initComponents();
        this.u = u;
        this.f = f;
        fillList();

    }

    private void fillList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        try {
            friends = FriendController.suggestFriends(u.getID());

            for (User user : friends) {
                listModel.addElement(user.getUsername());
            }

            list.setModel(listModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving friend suggestions.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        add = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suggested Friends");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(0, 51, 102));
        label.setText("     Suggested Friends");

        add.setBackground(new java.awt.Color(0, 51, 102));
        add.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add Friend");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(add)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (u == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int j = list.getSelectedIndex();
        try {
            if (j >= -1) {
                User selectedFriend = friends.get(j);
                FriendsManager.sendFriendRequest(u.getID(), selectedFriend.getID());
                fillList();

                JOptionPane.showMessageDialog(null, "Friend Request Sent Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a friend to add.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Handle any errors that occur
            JOptionPane.showMessageDialog(null, "Error occurred while sending the friend request.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_addActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (u == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            f.setVisible(true);
            f.setLocation(null);
            setVisible(false);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton add;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> list;
    // End of variables declaration//GEN-END:variables
}
