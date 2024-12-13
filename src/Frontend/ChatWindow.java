/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import connecthub.ChatWatcher;
import connecthub.entities.Chat;
import connecthub.entities.Content;
import connecthub.entities.Message;
import connecthub.entities.Profile;
import connecthub.entities.User;
import connecthub.mappers.ChatMapper;
import connecthub.mappers.MessageMapper;
import connecthub.mappers.ProfileMapper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ChatWindow extends javax.swing.JFrame {

    FriendsList friendslist;

    public ChatWindow(User user, int friendID, User friendUser, FriendsList friendslist) {
        this.friendslist = friendslist;
        this.user = user;
        userOneId = user.getID(); // Current user
        userTwoId = friendID;     // Friend

        initComponents();

        // Get the friend's profile
        Optional<Profile> optProfile = ProfileMapper.get(friendID);
        if (optProfile.isPresent()) {
            Profile profileFriend = optProfile.get();
            Show(friendUser.getUsername(), profileFriend.getProfilePhotoPath(), friendUser.getStatus());

            // Retrieve the chat between the two users
            Optional<Chat> chat = ChatMapper.get(userOneId, userTwoId);
            if (chat.isPresent()) {
                chatId = chat.get().getID();
            } else {
                // Handle case where the chat doesn't exist, if needed
                System.out.println("Chat not found.");
            }

            // Initialize ChatWatcher and set up the UI
            chatWatcher = new ChatWatcher();
            messagesArea.setEditable(false); // Make it read-only
            startFetchingMessages();
        } else {
            // Handle case where the friend's profile is not found
            System.out.println("Friend's profile not found.");
        }
    }

    private void Show(String friendUserName, String photoPath, String status) {

        jLabel1.setText("<html><p style='width: 120 px;'>" + friendUserName + "<html><br>" + status + "</p></html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setPreferredSize(new Dimension(400, 120)); // Explicit size for content label

        if (photoPath != null && !photoPath.isEmpty()) {
            try {
                ImageIcon originalIcon = new ImageIcon(photoPath);
                Image img = originalIcon.getImage();

                // Resize the image to fit the label
                Image scaledImg = img.getScaledInstance(125, 95, Image.SCALE_SMOOTH); // Use fixed dimensions
                jLabel2.setIcon(new ImageIcon(scaledImg));
            } catch (Exception e) {
                System.out.println("ana gebtk hena");
                jLabel2.setText("Image not found.");
                e.printStackTrace();
            }
        } else {
            jLabel2.setText("No image available.");
        }

        jLabel2.setPreferredSize(new Dimension(125, 95));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        newMessage = new javax.swing.JTextArea();
        sendMessage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesArea = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        newMessage.setColumns(20);
        newMessage.setRows(5);
        jScrollPane2.setViewportView(newMessage);

        sendMessage.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        sendMessage.setText("Send");
        sendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageActionPerformed(evt);
            }
        });

        messagesArea.setToolTipText("");
        jScrollPane1.setViewportView(messagesArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(sendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageActionPerformed
        // TODO add your handling code here:
        String messageContent = newMessage.getText().trim();
        if (!messageContent.isEmpty()) {
            // Create and save the message
            Message toSendMessage = new Message(this.userOneId, this.userTwoId, chatId, messageContent);
            MessageMapper.create(toSendMessage);

            // Append the message to the chat area instantly
            appendStyledMessage(messagesArea, messageContent + "\n", false);
            fetchedMessages.add(toSendMessage);

            messagesArea.setCaretPosition(messagesArea.getDocument().getLength());

            // Clear the input field
            newMessage.setText("");
        }

    }//GEN-LAST:event_sendMessageActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "User or Newsfeed data is missing. Please log in again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            friendslist.setVisible(true);
            friendslist.setLocationRelativeTo(null);
            setVisible(false);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowClosing
    private void startFetchingMessages() {
        new Thread(() -> {
            chatWatcher.startWatching(this.userOneId, this.userTwoId);
            while (true) {
                try {
                    List<Message> newMessages = chatWatcher.getNewMessages();
                    if (!newMessages.isEmpty()) {
                        SwingUtilities.invokeLater(() -> {
                            for (Message message : newMessages) {
                                if (!fetchedMessages.contains(message)) {
                                    String formattedMessage;
                                    if (message.getRecieverId() == this.userOneId) {
                                        formattedMessage = message.getContent();
                                        appendStyledMessage(messagesArea, formattedMessage, true);
                                    } else {
                                        formattedMessage = message.getContent();
                                        appendStyledMessage(messagesArea, formattedMessage, false);
                                    }
                                    fetchedMessages.add(message);
                                    message.setStatus("seen");
                                    MessageMapper.update(message.getID(), message);
                                }
                            }
                            messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
                        });
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    private void appendStyledMessage(javax.swing.JTextPane textPane, String message, boolean isReceived) {

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setFontSize(attributes, 16); // Set font size

        // Set padding between messages
        StyleConstants.setSpaceAbove(attributes, 5); // Space above the message
        StyleConstants.setSpaceBelow(attributes, 5); // Space below the message

        if (isReceived) {
            StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
            StyleConstants.setBackground(attributes, new Color(173, 216, 230)); // Light blue background
            StyleConstants.setForeground(attributes, Color.BLACK); // Black text for readability
        } else {
            StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
            StyleConstants.setBackground(attributes, new Color(144, 238, 144)); // Light green background
            StyleConstants.setForeground(attributes, Color.BLACK); // Black text for readability
        }

        try {
            int start = doc.getLength(); // Get the start position for the new message
            doc.insertString(start, message + "\n", attributes); // Insert the message

            // Apply the style to the inserted text
            doc.setParagraphAttributes(start, message.length(), attributes, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private ChatWatcher chatWatcher;
    private int userOneId;
    private int userTwoId;
    private int chatId;
    private List<Message> fetchedMessages = new ArrayList<>();
    User user;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane messagesArea;
    private javax.swing.JTextArea newMessage;
    private javax.swing.JButton sendMessage;
    // End of variables declaration//GEN-END:variables
}
