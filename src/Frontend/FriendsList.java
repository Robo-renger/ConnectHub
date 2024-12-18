/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.FriendsManager;
import connecthub.controllers.FriendController;
import connecthub.entities.Friend;
import connecthub.entities.User;
import connecthub.mappers.FriendMapper;
import connecthub.mappers.UserMapper;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mahinour Mohamed
 */
public class FriendsList extends javax.swing.JFrame {

    User u;
    Newsfeed newsFeed;
    FriendsManagement f;
    List<User> friends;

    public FriendsList(User user, Newsfeed newsFeed)  {
        initComponents();
        this.u = user;
        this.newsFeed = newsFeed;
        this.f = null;
        FriendsTable();
    }
    
    public FriendsList(User u, FriendsManagement f) {
        initComponents();
        this.u = u;
        this.f = f;
        this.newsFeed = null;
        FriendsTable();
    }

    // Fill the table
    private void FriendsTable() {
        try {
            friends = FriendController.getAllFriends(u.getID());
            DefaultTableModel t = (DefaultTableModel) table.getModel();

            // Clear existing rows before adding new data
            t.setRowCount(0);

            // Add updated rows to the table
            for (User friend : friends) {
                t.addRow(new Object[]{friend.getUsername(), friend.getStatus()});
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        block = new javax.swing.JToggleButton();
        remove = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        chat = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My friends");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        block.setBackground(new java.awt.Color(0, 51, 102));
        block.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        block.setForeground(new java.awt.Color(255, 255, 255));
        block.setText("Block");
        block.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockActionPerformed(evt);
            }
        });

        remove.setBackground(new java.awt.Color(0, 51, 102));
        remove.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        remove.setForeground(new java.awt.Color(255, 255, 255));
        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);

        chat.setBackground(new java.awt.Color(0, 51, 102));
        chat.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        chat.setForeground(new java.awt.Color(255, 255, 255));
        chat.setText("Chat");
        chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(block)
                    .addComponent(remove)
                    .addComponent(chat))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockActionPerformed
        if (u == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = table.getSelectedRow();
            if (index >= 0) {
                FriendsManager.blockUser(u.getID(), friends.get(index).getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Blocked Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                FriendsTable();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_blockActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        if (u == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = table.getSelectedRow();
            if (index >= 0) {
                FriendController.removeFriend(u.getID(), friends.get(index).getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Remove Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                FriendsTable();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removeActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (u == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if(newsFeed == null)
            {
                f.setVisible(true);
                f.setLocationRelativeTo(null);
                setVisible(false);
            }else {
                newsFeed.setVisible(true);
                newsFeed.setLocationRelativeTo(null);
                setVisible(false);
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_formWindowClosing

    private void chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatActionPerformed
        if (u == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = table.getSelectedRow();
            if (index >= 0) {
                ChatWindow chat = new ChatWindow(this.u, friends.get(index).getID(), friends.get(index), this);
                chat.setVisible(true);
                chat.setLocationRelativeTo(null);
                setVisible(false);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_chatActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton block;
    private javax.swing.JToggleButton chat;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton remove;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
