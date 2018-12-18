package EmployeeChat;

import Security.AESsencSEC;
import com.socket.Message;
import com.socket.SocketClient;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ChatFrame extends javax.swing.JFrame {

    protected int CEASERKEY = 3;

    protected String SECUREKEY = "theisbestititkey";

    public SocketClient client;
    public int port;
    public String serverAddr, username;
    public Thread clientThread;
    boolean IsConnectedToServer = false;
    SocketClient Client;
    ButtonGroup group = new ButtonGroup();

    public ChatFrame() {
        initComponents();

        String jp = JOptionPane.showInputDialog("enter user name");
        jScrollPane1.setVisible(false);
        jRadioButton1.setSelected(true);
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        group.add(jRadioButton3);
        group.add(jRadioButton4);
        group.add(jRadioButton5);
        this.username = String.valueOf(jp);
        ConnectToServer();

        this.setTitle(username);
    }

    public void ConnectToServer() {
        try {
            serverAddr = "127.0.0.1";
            port = 13000;
            Client = new SocketClient(serverAddr, port, this);
            Client.send(new Message("Connect", username, "CheckServer", "SERVER"));
            Client.send(new Message("KEYREQUIEST", username, "KEYREQUIEST", "SERVER"));
            clientThread = new Thread(Client);
            clientThread.start();
            IsConnectedToServer = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
            IsConnectedToServer = false;

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dynamicPanelList1 = new EmployeeChat.DynamicPanelList();
        dynamicPanelList2 = new EmployeeChat.DynamicPanelList();
        dynamicPanelList3 = new EmployeeChat.DynamicPanelList();
        dynamicPanelList4 = new EmployeeChat.DynamicPanelList();
        dynamicPanelList5 = new EmployeeChat.DynamicPanelList();
        dynamicPanelList6 = new EmployeeChat.DynamicPanelList();
        dynamicPanelList7 = new EmployeeChat.DynamicPanelList();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        dynamicPanelList8 = new EmployeeChat.DynamicPanelList();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel5.setText("Message : ");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton4.setText("Send Message ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton1.setText("PlanText");

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton2.setText("Hash");

        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton3.setText("Ceaser");

        jRadioButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton4.setText("PlayFair");
        jRadioButton4.setToolTipText("");

        jRadioButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton5.setText("AES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4)
                                .addGap(74, 74, 74)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dynamicPanelList8, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jRadioButton3)
                                    .addComponent(jRadioButton4)
                                    .addComponent(jRadioButton5))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dynamicPanelList8, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        SendMessage();


    }//GEN-LAST:event_jButton4ActionPerformed
    public String SelectedEncryption() {
        String RES = "";
        if (this.jRadioButton1.isSelected()) {
            RES = this.jRadioButton1.getText();
        }
        if (this.jRadioButton2.isSelected()) {
            RES = this.jRadioButton2.getText();
        }
        if (this.jRadioButton3.isSelected()) {
            RES = this.jRadioButton3.getText();
        }

        if (this.jRadioButton4.isSelected()) {
            RES = this.jRadioButton4.getText();
        }

        if (this.jRadioButton5.isSelected()) {
            RES = this.jRadioButton5.getText();
        }
        return RES;
    }

    public String ENCYPTEDTEXT() {
        String RETURNDATA = jTextField4.getText();
        if (SelectedEncryption() == "PlanText") {
            RETURNDATA = jTextField4.getText();
        }
        if (SelectedEncryption() == "Ceaser") {
            RETURNDATA = Security.Encryption.CeaserEncrypt(jTextField4.getText(), CEASERKEY);
        }
        if (SelectedEncryption() == "PlayFair") {
            Security.Decryption.playfair P = new Security.Decryption.playfair();
            P.setKey(SECUREKEY.toLowerCase());
            P.KeyGen();
            RETURNDATA = P.Encript(jTextField4.getText());
        }

        if (SelectedEncryption() == "AES") {
            AESsencSEC.keyValue = SECUREKEY;

            try {
                RETURNDATA = AESsencSEC.encrypt(jTextField4.getText());
            } catch (Exception ex) {
                Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RETURNDATA;
    }

    public void SendMessage() {
        try {
            //      System.out.println("key : " + this.SECUREKEY);
            String ORGmsg = jTextField4.getText();
            String msg = ENCYPTEDTEXT();

            MSG NEWMSG = new MSG(Calendar.getInstance().getTime(), null, msg, null, "All", username);

            NEWMSG.EncryptionTYPE = SelectedEncryption();
            if (!msg.isEmpty()) {
                jTextField4.setText("");
                Client.send(new Message("message", username, NEWMSG, "All"));
                NEWMSG.MsgContent = ORGmsg;
                this.dynamicPanelList8.ADDMessage(NEWMSG, false);

            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }


    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            SendMessage();

        }
    }//GEN-LAST:event_jTextField4KeyReleased
    public void HandleMsg(Message msg) {
        try {

            if (msg.type.toString().equals("KEYREQUIEST")) {
                this.SECUREKEY = msg.content.toString();

            } else {

                MSG NEWMSG = (MSG) msg.content;
                String RETURNDATA = "";
                if (NEWMSG.EncryptionTYPE.equals("PlanText")) {
                    RETURNDATA = NEWMSG.MsgContent;
                }
                if (NEWMSG.EncryptionTYPE.equals("Ceaser")) {
                    RETURNDATA = Security.Decryption.CeaserDecrypt(NEWMSG.MsgContent, CEASERKEY);
                }
                if (NEWMSG.EncryptionTYPE.equals("PlayFair")) {
                    Security.Decryption.playfair P = new Security.Decryption.playfair();
                    P.setKey(SECUREKEY.toLowerCase());
                    P.KeyGen();
                    RETURNDATA = P.Decript(NEWMSG.MsgContent);
                }
                if (NEWMSG.EncryptionTYPE.equals("AES")) {
                    AESsencSEC.keyValue = SECUREKEY;

                    try {
                        RETURNDATA = AESsencSEC.decrypt(NEWMSG.MsgContent);
                    } catch (Exception ex) {
                        Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                NEWMSG.MsgContent = RETURNDATA;

                if (!msg.sender.equals(username)) {

                    //   MSG NEWMSG = (MSG) msg.content;
                    NEWMSG.ReciveDate = Calendar.getInstance().getTime();
                    if (!NEWMSG.equals(null)) {
                        this.dynamicPanelList8.ADDMessage(NEWMSG, true);
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Look & Feel exception");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EmployeeChat.DynamicPanelList dynamicPanelList1;
    private EmployeeChat.DynamicPanelList dynamicPanelList2;
    private EmployeeChat.DynamicPanelList dynamicPanelList3;
    private EmployeeChat.DynamicPanelList dynamicPanelList4;
    private EmployeeChat.DynamicPanelList dynamicPanelList5;
    private EmployeeChat.DynamicPanelList dynamicPanelList6;
    private EmployeeChat.DynamicPanelList dynamicPanelList7;
    private EmployeeChat.DynamicPanelList dynamicPanelList8;
    public javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
