package Frontend;

import connecthub.CredentialsValidation;
import connecthub.entities.Profile;
import connecthub.entities.User;
import connecthub.mappers.ProfileMapper;
import connecthub.mappers.UserMapper;
import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Login extends javax.swing.JFrame {

    private FrontProfile fp;

    public Login() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        login = new javax.swing.JToggleButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Email");

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Password");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        login.setBackground(new java.awt.Color(0, 51, 102));
        login.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(login)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FirstPage.getInstanceOf().setVisible(true);
        FirstPage.getInstanceOf().setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowClosing

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        String loginEmail = email.getText().trim();
        String loginUserPassword = new String(jPasswordField1.getPassword());

        // Validate email and password fields
        if (loginEmail.isEmpty() || loginUserPassword.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Email or password cannot be empty!",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            // Attempt login
            Optional<User> optUser = UserMapper.login(loginEmail, loginUserPassword);

            if (optUser.isPresent()) {
                User foundUser = optUser.get();

                // Fetch user's profile
                Optional<Profile> optProfile = ProfileMapper.get(foundUser.getID());
                if (optProfile.isPresent()) {
                    Profile profileUser = optProfile.get();

                    // Display success message
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Login successful!",
                            "Success",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    // Proceed to the profile screen
                    FrontProfile fp = FrontProfile.getInstanceOf();
                    fp.setU(foundUser);
                    fp.setP(profileUser);
                    fp.setVisible(true);
                    fp.setLocationRelativeTo(null); // Center the profile window
                    setVisible(false); // Hide the login window
                    return; // Exit the method after successful login
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Profile not found for the logged-in user.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                // User not found or invalid credentials
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Username or password is incorrect.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            // Handle unexpected errors
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "An error occurred during login: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }//GEN-LAST:event_loginActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JToggleButton login;
    // End of variables declaration//GEN-END:variables
}
