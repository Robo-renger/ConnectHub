/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import connecthub.FriendsManager;
import connecthub.GroupAuthorityManager;
import connecthub.controllers.FriendController;
import connecthub.controllers.GroupController;
import connecthub.entities.Friend;
import connecthub.entities.Group;
import connecthub.entities.Profile;
import connecthub.entities.User;
import connecthub.entities.UserGroup;
import connecthub.exceptions.InvalidDataException;
import connecthub.mappers.ProfileMapper;
import connecthub.mappers.UserGroupMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Search extends javax.swing.JFrame {

    User u;
    Profile profile;
    List<User> users;
    List<Group> groups;
    List<Object> entities = new ArrayList<>();

    public Search(User u, Profile profile) {
        initComponents();
        this.u = u;
        this.profile = profile;
        add.setVisible(false);
        join.setVisible(false);
        block.setVisible(false);
        remove.setVisible(false);
        viewProfile.setVisible(false);
        viewGroup.setVisible(false);
        leaveGroup.setVisible(false);
        initListSelectionListener();
    }

    private void fillList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (User user : users) {
            if (u.getID() != user.getID()) {
                listModel.addElement(user.getUsername());
            }
        }
        for (Group group : groups) {
            if (u.getID() != group.getCreatorID()) {
                listModel.addElement(group.getName());
                System.out.println("aloooo");

            }
        }
        list.setModel(listModel);
    }

    private void onListSelectionChanged(javax.swing.event.ListSelectionEvent evt) {
        String selectedValue = list.getSelectedValue();

        if (selectedValue != null) {
            // Check if the selected item is a User or Group
            boolean isUserSelected = false;
            boolean isGroupSelected = false;

            // Reset visibility of buttons
            add.setVisible(false);
            join.setVisible(false);
            block.setVisible(false);
            remove.setVisible(false);
            viewProfile.setVisible(false);
            viewGroup.setVisible(false);
            leaveGroup.setVisible(false);
            // Check if the selected value corresponds to a user
            for (User user : users) {
                if (selectedValue.equals(user.getUsername())) {
                    isUserSelected = true;
                    break;
                }
            }

            // Check if the selected value corresponds to a group
            for (Group group : groups) {
                if (selectedValue.equals(group.getName())) {
                    isGroupSelected = true;
                    break;
                }
            }

            // Show the appropriate button based on selection
            if (isUserSelected) {
                add.setVisible(true);  // Show the add button if a user is selected
                block.setVisible(true);
                remove.setVisible(true);
                viewProfile.setVisible(true);
            } else if (isGroupSelected) {
                join.setVisible(true); // Show the join button if a group is selected
                leaveGroup.setVisible(true);
                viewGroup.setVisible(true);
            }
        }
    }

    private void initListSelectionListener() {
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onListSelectionChanged(evt);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        search = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        add = new javax.swing.JToggleButton();
        join = new javax.swing.JToggleButton();
        viewGroup = new javax.swing.JToggleButton();
        viewProfile = new javax.swing.JToggleButton();
        leaveGroup = new javax.swing.JToggleButton();
        block = new javax.swing.JToggleButton();
        remove = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        label.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(0, 51, 102));
        label.setText("Name:");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(0, 51, 102));
        search.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(list);

        add.setBackground(new java.awt.Color(0, 51, 102));
        add.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add Friend");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        join.setBackground(new java.awt.Color(0, 51, 102));
        join.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        join.setForeground(new java.awt.Color(255, 255, 255));
        join.setText("Join");
        join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinActionPerformed(evt);
            }
        });

        viewGroup.setBackground(new java.awt.Color(0, 51, 102));
        viewGroup.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        viewGroup.setForeground(new java.awt.Color(255, 255, 255));
        viewGroup.setText("View Group");
        viewGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGroupActionPerformed(evt);
            }
        });

        viewProfile.setBackground(new java.awt.Color(0, 51, 102));
        viewProfile.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        viewProfile.setForeground(new java.awt.Color(255, 255, 255));
        viewProfile.setText("View profile");
        viewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProfileActionPerformed(evt);
            }
        });

        leaveGroup.setBackground(new java.awt.Color(0, 51, 102));
        leaveGroup.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        leaveGroup.setForeground(new java.awt.Color(255, 255, 255));
        leaveGroup.setText("Leave group");
        leaveGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveGroupActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(leaveGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(viewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(viewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(join, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(search))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(join))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewGroup)
                    .addComponent(viewProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leaveGroup)
                    .addComponent(block))
                .addGap(9, 9, 9)
                .addComponent(remove))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed

    }//GEN-LAST:event_nameActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        try {
            entities = new ArrayList<>();
            if (!name.getText().isEmpty()) {
                users = FriendController.searchUsers(name.getText());
                groups = GroupController.search(name.getText());
                for (User user : users) {
                    System.out.println("USER");
                    System.out.println(user);
                }
                for (User user : users) {
                    entities.add(user);
                }

                for (Group group : groups) {
                    entities.add(group);

                }
                fillList();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_searchActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        try {

            List<User> friends = FriendController.getAllFriends(u.getID());
            if (entities.get(list.getSelectedIndex()) instanceof User) {
                User optUser = (User) entities.get(list.getSelectedIndex());
                for (User friend : friends) {
                    if (optUser.getID() == (friend.getID())) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Already friend!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                FriendsManager.sendFriendRequest(u.getID(), optUser.getID());
                fillList();
                javax.swing.JOptionPane.showMessageDialog(null, "Added Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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

            FrontProfile frontProfile = FrontProfile.getInstanceOf(u, profile);
            frontProfile.setVisible(true);
            frontProfile.setLocationRelativeTo(null);
            setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();  // Log the exception's stack trace
            JOptionPane.showMessageDialog(this,
                    "An error occurred while retrieving the profile. Please try again later.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_formWindowClosing

    private void joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinActionPerformed
        try {
            List<Group> userGroups = GroupController.joinedGroups(u.getID());
            if (entities.get(list.getSelectedIndex()) instanceof Group) {
                Group optGroup = (Group) entities.get(list.getSelectedIndex());
                for (Group userGroup : userGroups) {
                    if (optGroup.getID() == userGroup.getID() || optGroup.getCreatorID() == u.getID()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Already Joined!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    GroupAuthorityManager.sendMembershipRequest(optGroup.getID(), u.getID());
                }
                javax.swing.JOptionPane.showMessageDialog(null, "Joined Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_joinActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void viewGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewGroupActionPerformed
        try {
            List<Group> userGroups = GroupController.joinedGroups(u.getID());
            if (entities.get(list.getSelectedIndex()) instanceof Group) {
                Group optGroup = (Group) entities.get(list.getSelectedIndex());
                GroupProfile groupProfile = new GroupProfile(optGroup, u, this);
                groupProfile.setVisible(true);
                groupProfile.setLocationRelativeTo(null);
                setVisible(false);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_viewGroupActionPerformed

    private void viewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProfileActionPerformed
        try {

            List<User> friends = FriendController.getAllFriends(u.getID());
            if (entities.get(list.getSelectedIndex()) instanceof User) {
                User optUser = (User) entities.get(list.getSelectedIndex());
                Optional<Profile> thisProfile = ProfileMapper.get(optUser.getID());
                if (thisProfile.isPresent()) {
                    Profile foundProfile = thisProfile.get();
                    FrontProfile frontProfile = FrontProfile.getInstanceOf(optUser, foundProfile, this);
                    frontProfile.setVisible(true);
                    frontProfile.setLocationRelativeTo(null);
                    setVisible(false);
                }
                else
                     JOptionPane.showMessageDialog(this,
                "Profile not found. Please try again later.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewProfileActionPerformed

    private void leaveGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveGroupActionPerformed
        try {
            List<Group> userGroups = GroupController.joinedGroups(u.getID());
            if (entities.get(list.getSelectedIndex()) instanceof Group) {
                Group optGroup = (Group) entities.get(list.getSelectedIndex());
                for (Group userGroup : userGroups) {
                    if (optGroup.getID() == userGroup.getID()) {
                        GroupController.leave(optGroup.getID(), u.getID());
                        javax.swing.JOptionPane.showMessageDialog(null, "Leave Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        return;
                    } if (optGroup.getCreatorID() == u.getID()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Creator cannot leave!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;                   
                    } 

                }
                 javax.swing.JOptionPane.showMessageDialog(null, "Not member!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_leaveGroupActionPerformed

    private void blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockActionPerformed
        try {

            if (entities.get(list.getSelectedIndex()) instanceof User) {
                User optUser = (User) entities.get(list.getSelectedIndex());
                FriendsManager.blockUser(u.getID(), optUser.getID());
                fillList();
                javax.swing.JOptionPane.showMessageDialog(null, "Blocked Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_blockActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        try {

            List<User> friends = FriendController.getAllFriends(u.getID());
            if (entities.get(list.getSelectedIndex()) instanceof User) {
                User optUser = (User) entities.get(list.getSelectedIndex());
                for (User friend : friends) {
                    if (optUser.getID() == (friend.getID())) {
                        FriendController.removeFriend(u.getID(), optUser.getID());
                        fillList();
                        javax.swing.JOptionPane.showMessageDialog(null, "Removed Successfully!", "success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                              return;
                    }
                }
                javax.swing.JOptionPane.showMessageDialog(null, "Not friend!", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton add;
    private javax.swing.JToggleButton block;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton join;
    private javax.swing.JLabel label;
    private javax.swing.JToggleButton leaveGroup;
    private javax.swing.JList<String> list;
    private javax.swing.JTextField name;
    private javax.swing.JToggleButton remove;
    private javax.swing.JToggleButton search;
    private javax.swing.JToggleButton viewGroup;
    private javax.swing.JToggleButton viewProfile;
    // End of variables declaration//GEN-END:variables
}
