/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.controllers.GroupController;
import connecthub.entities.Group;
import connecthub.entities.User;
import connecthub.mappers.GroupMapper;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahinour Mohamed
 */
public class GroupsList extends javax.swing.JFrame {

    /**
     * Creates new form GroupsList
     */
    User user;
    GroupsManagement group;
    List<Group> groups;
    List<Group> creatorGroups;

    public GroupsList(User user, GroupsManagement group) {
        initComponents();
        this.user = user;
        this.group = group;
        fillList();
    }

    private void fillList() {
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();
        groups = GroupController.joinedGroups(user.getID());
        creatorGroups = GroupMapper.getAllCreatedGroups(user.getID());
        for (Group group : groups) {
            listModel.addElement(group.getName());
        }
        for (Group creatorGroup : creatorGroups) {
            listModel.addElement(creatorGroup.getName());
            groups.add(creatorGroup);
        }

        list.setModel(listModel);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        leave = new javax.swing.JToggleButton();
        view = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My groups");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(0, 51, 102));
        label.setText("            My Groups");

        jScrollPane1.setViewportView(list);

        leave.setBackground(new java.awt.Color(0, 51, 102));
        leave.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        leave.setForeground(new java.awt.Color(255, 255, 255));
        leave.setText("Leave");
        leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(leave, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view)
                    .addComponent(leave))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = list.getSelectedIndex();
            if (index >= 0) {
                System.out.println(groups.get(index).getID());
                  if(user.getID()==groups.get(index).getCreatorID())
                javax.swing.JOptionPane.showMessageDialog(null, "Creator cannot leave the group", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                  else{
                GroupController.leave(groups.get(index).getID(), user.getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Left Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                fillList();
            }}
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
       
    }//GEN-LAST:event_leaveActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = list.getSelectedIndex();
            if (index >= 0) {
                GroupProfile group = new GroupProfile(groups.get(index), user, this);
                group.setVisible(true);
                group.setLocationRelativeTo(null);
                setVisible(false);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_viewActionPerformed

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
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JToggleButton leave;
    private javax.swing.JList<String> list;
    private javax.swing.JToggleButton view;
    // End of variables declaration//GEN-END:variables
}
