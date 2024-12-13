/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.GroupAuthorityManager;
import connecthub.controllers.GroupController;
import connecthub.entities.Group;
import connecthub.entities.User;
import connecthub.mappers.UserMapper;
import java.awt.Image;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahinour Mohamed
 */
public class GroupProfile extends javax.swing.JFrame {

    /**
     * Creates new form GroupProfile
     */
    Group group;
    User user;
    GroupsList groupList;
    Search search;
    List<User> members;

    public GroupProfile(Group group, User user, GroupsList groupList) {
        initComponents();

        if (group == null || user == null) {
            JOptionPane.showMessageDialog(this,
                    "Error initializing profile: User or profile data is missing.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.group = group;
        this.user = user;
        this.groupList = groupList;
        search = null;
        // Set user cover photo
        ImageIcon cover = new ImageIcon(group.getImagePath());
        Image coverImg = cover.getImage();
        Image scaledImg2 = coverImg.getScaledInstance(coverPhoto.getWidth(), coverPhoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon coverScaledIcon = new ImageIcon(scaledImg2);

        coverPhoto.setIcon(coverScaledIcon);

        name.setText(group.getName());
        description.setText(group.getDescription());
        if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Member")) {
            addAdmin.setVisible(false);
            demoteAdmin.setVisible(false);
            deleteGroup.setVisible(false);
            removeMember.setVisible(false);
            membershipRequests.setVisible(false);
        } else if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Admin")) {
            addAdmin.setVisible(false);
            demoteAdmin.setVisible(false);
            deleteGroup.setVisible(false);
        } else if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Creator")) {
        } else {
            addAdmin.setVisible(false);
            demoteAdmin.setVisible(false);
            deleteGroup.setVisible(false);
            removeMember.setVisible(false);
            membershipRequests.setVisible(false);
            leave.setVisible(false);
            posts.setVisible(false);
        }
        fillList();
    }

    public GroupProfile(Group group, User user, Search search) {
        initComponents();

        if (group == null || user == null) {
            JOptionPane.showMessageDialog(this,
                    "Error initializing profile: User or profile data is missing.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.group = group;
        this.user = user;
        this.search = search;
        groupList = null;
        // Set user cover photo
        ImageIcon cover = new ImageIcon(group.getImagePath());
        Image coverImg = cover.getImage();
        Image scaledImg2 = coverImg.getScaledInstance(coverPhoto.getWidth(), coverPhoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon coverScaledIcon = new ImageIcon(scaledImg2);

        coverPhoto.setIcon(coverScaledIcon);

        name.setText(group.getName());
        description.setText(group.getDescription());
        if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Member")) {
            addAdmin.setVisible(false);
            demoteAdmin.setVisible(false);
            deleteGroup.setVisible(false);
            removeMember.setVisible(false);
            membershipRequests.setVisible(false);
        } else if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Admin")) {
            addAdmin.setVisible(false);
            demoteAdmin.setVisible(false);
            deleteGroup.setVisible(false);
        } else if (GroupAuthorityManager.validateRole(group.getID(), user.getID()).equals("Creator")) {
        } else {
            addAdmin.setVisible(false);
            demoteAdmin.setVisible(false);
            deleteGroup.setVisible(false);
            removeMember.setVisible(false);
            membershipRequests.setVisible(false);
            leave.setVisible(false);
            posts.setVisible(false);
        }
        fillList();
    }

    public void fillList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        members = GroupController.getJoinedMembers(group.getID());
        for (User user : members) {
            listModel.addElement(user.getUsername());
        }
//        Optional<User> user=UserMapper.get(group.getCreatorID());
//        if(user.isPresent())
//        {  User foundUser = user.get();
//          members.add(foundUser);
//        listModel.addElement(foundUser.getUsername());
//        }
        membersList.setModel(listModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coverPhoto = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        leave = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        membersList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        addAdmin = new javax.swing.JToggleButton();
        demoteAdmin = new javax.swing.JToggleButton();
        removeMember = new javax.swing.JToggleButton();
        posts = new javax.swing.JToggleButton();
        deleteGroup = new javax.swing.JToggleButton();
        membershipRequests = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Group Profile");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        coverPhoto.setBackground(new java.awt.Color(0, 51, 102));
        coverPhoto.setOpaque(true);

        leave.setBackground(new java.awt.Color(0, 51, 102));
        leave.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        leave.setForeground(new java.awt.Color(255, 255, 255));
        leave.setText("Leave");
        leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(membersList);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Group members");

        addAdmin.setBackground(new java.awt.Color(0, 51, 102));
        addAdmin.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        addAdmin.setForeground(new java.awt.Color(255, 255, 255));
        addAdmin.setText("Promote admin");
        addAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminActionPerformed(evt);
            }
        });

        demoteAdmin.setBackground(new java.awt.Color(0, 51, 102));
        demoteAdmin.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        demoteAdmin.setForeground(new java.awt.Color(255, 255, 255));
        demoteAdmin.setText("Demote admin");
        demoteAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoteAdminActionPerformed(evt);
            }
        });

        removeMember.setBackground(new java.awt.Color(0, 51, 102));
        removeMember.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        removeMember.setForeground(new java.awt.Color(255, 255, 255));
        removeMember.setText("Remove member");
        removeMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberActionPerformed(evt);
            }
        });

        posts.setBackground(new java.awt.Color(0, 51, 102));
        posts.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        posts.setForeground(new java.awt.Color(255, 255, 255));
        posts.setText("Posts");
        posts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postsActionPerformed(evt);
            }
        });

        deleteGroup.setBackground(new java.awt.Color(204, 0, 0));
        deleteGroup.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        deleteGroup.setForeground(new java.awt.Color(255, 255, 255));
        deleteGroup.setText("Delete Group");
        deleteGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGroupActionPerformed(evt);
            }
        });

        membershipRequests.setBackground(new java.awt.Color(0, 51, 102));
        membershipRequests.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        membershipRequests.setForeground(new java.awt.Color(255, 255, 255));
        membershipRequests.setText("Membership requests");
        membershipRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                membershipRequestsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(coverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(19, 19, 19))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deleteGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(demoteAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(posts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeMember, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(membershipRequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addComponent(leave, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(coverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeMember)
                    .addComponent(addAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posts)
                    .addComponent(demoteAdmin)
                    .addComponent(leave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteGroup)
                    .addComponent(membershipRequests))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveActionPerformed
        if (user == null || group == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (user.getID() == group.getCreatorID()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Creator cannot leave the group", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                GroupController.leave(group.getID(), user.getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Left successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                groupList.setVisible(true);
                groupList.setLocationRelativeTo(null);
                setVisible(false);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_leaveActionPerformed

    private void addAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = membersList.getSelectedIndex();
            if (GroupAuthorityManager.validateRole(group.getID(), members.get(index).getID()).equals("Admin")) {
                javax.swing.JOptionPane.showMessageDialog(null, "Already admin!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
            if (index >= 0) {
                GroupAuthorityManager.promoteToAdmin(group.getID(), members.get(index).getID(), user.getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Promote to admin Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                fillList();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_addAdminActionPerformed

    private void demoteAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoteAdminActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = membersList.getSelectedIndex();
            if (!GroupAuthorityManager.validateRole(group.getID(), members.get(index).getID()).equals("Admin")) {
                javax.swing.JOptionPane.showMessageDialog(null, "Not admin!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
            if (index >= 0) {
                GroupAuthorityManager.demoteFromAdmin(group.getID(), members.get(index).getID(), user.getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Demote from admin Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                fillList();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_demoteAdminActionPerformed

    private void removeMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = membersList.getSelectedIndex();
            if (members.get(index).getID() == group.getCreatorID()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Creator cannot be removed!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (index >= 0) {
                GroupAuthorityManager.removeMember(group.getID(), members.get(index).getID(), user.getID());
                javax.swing.JOptionPane.showMessageDialog(null, "Remove member Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                fillList();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_removeMemberActionPerformed

    private void postsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postsActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            GroupPosts groupPosts = new GroupPosts(group, user, this);
            groupPosts.setVisible(true);
            groupPosts.setLocationRelativeTo(null);
            setVisible(false);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_postsActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (search == null) {
                groupList.setVisible(true);
                groupList.setLocationRelativeTo(null);
                setVisible(false);
            } else {
                search.setVisible(true);
                search.setLocationRelativeTo(null);
                setVisible(false);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_formWindowClosing

    private void deleteGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGroupActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            GroupAuthorityManager.deleteGroup(group.getID(), user.getID());
            javax.swing.JOptionPane.showMessageDialog(null, "Delete group Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            groupList.setVisible(true);
            groupList.setLocationRelativeTo(null);
            setVisible(false);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_deleteGroupActionPerformed

    private void membershipRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_membershipRequestsActionPerformed
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            MembershipRequests membershipRequests = new MembershipRequests(group, this, user);
            membershipRequests.setVisible(true);
            membershipRequests.setLocationRelativeTo(null);
            setVisible(false);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_membershipRequestsActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addAdmin;
    private javax.swing.JLabel coverPhoto;
    private javax.swing.JToggleButton deleteGroup;
    private javax.swing.JToggleButton demoteAdmin;
    private javax.swing.JLabel description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton leave;
    private javax.swing.JList<String> membersList;
    private javax.swing.JToggleButton membershipRequests;
    private javax.swing.JLabel name;
    private javax.swing.JToggleButton posts;
    private javax.swing.JToggleButton removeMember;
    // End of variables declaration//GEN-END:variables
}
