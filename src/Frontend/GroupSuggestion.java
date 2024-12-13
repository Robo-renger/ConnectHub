/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.GroupAuthorityManager;
import connecthub.controllers.FriendController;
import connecthub.controllers.GroupController;
import connecthub.entities.Group;
import connecthub.entities.User;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GroupSuggestion extends javax.swing.JFrame {

    User user;
    GroupsManagement group;
    List<Group> groups;

    public GroupSuggestion(User user, GroupsManagement group) {
        initComponents();
        this.user = user;
        this.group = group;
        this.groups = GroupController.suggestGroups(this.user.getID());
        fillList();
    }

    private void fillList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        try {

            for (Group group : groups) {
                listModel.addElement(group.getName());
            }

            list.setModel(listModel);
            System.out.println(listModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving group suggestions.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        join = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suggested groups");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(0, 51, 102));
        label.setText("       Suggested Groups");

        jScrollPane1.setViewportView(list);

        join.setBackground(new java.awt.Color(0, 51, 102));
        join.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        join.setForeground(new java.awt.Color(255, 255, 255));
        join.setText("Join");
        join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(join, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(join)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int j = list.getSelectedIndex();
        try {
            if (j >= -1) {
                Group selectedGroup = groups.get(j);
                GroupAuthorityManager.sendMembershipRequest(selectedGroup.getID(), user.getID());
                fillList();

                JOptionPane.showMessageDialog(null, "Group join request Sent Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a group to join.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Handle any errors that occur
            JOptionPane.showMessageDialog(null, "Error occurred while sending the join request.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_joinActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            group.setVisible(true);
            group.setLocationRelativeTo(null);
            setVisible(false);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton join;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> list;
    // End of variables declaration//GEN-END:variables
}
