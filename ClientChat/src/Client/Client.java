/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tdh4vn
 */
public class Client extends JFrame{

    private JPanel panelMain;
    private JPanel panelBottom;
    private JPanel panelTop;
    private JButton btnLogin;
    private JTextArea txtChatLog;
    private JTextField txtUserName;
    private JTextField txtChatMessage;
    private JButton btnSend;
    private Socket socketClient;
    private DataInputStream din;
    private DataOutputStream dout;
            
    public Client() throws HeadlessException, IOException {
        super();
        this.setTitle("Client");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        socketClient = new Socket("127.0.0.1", 9090);
        din = new DataInputStream(socketClient.getInputStream());
        dout = new DataOutputStream(socketClient.getOutputStream());
        
        panelMain = new JPanel(new BorderLayout());
        
        panelBottom = new JPanel(new FlowLayout());
        txtChatMessage = new JTextField(20);
        btnSend = new JButton("Send");
        txtChatLog = new JTextArea(50,70);
        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dout.writeUTF(txtChatMessage.getText());
                    txtChatLog.append(txtChatMessage.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        panelBottom.add(txtChatMessage);
        panelBottom.add(btnSend);
        
        txtUserName = new JTextField(20);
        
        
        
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dout.writeUTF(txtUserName.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        panelTop = new JPanel(new FlowLayout());
        panelTop.add(txtUserName);
        panelTop.add(btnLogin);
        
                
        
        panelMain.add(panelTop, BorderLayout.NORTH);
        panelMain.add(txtChatLog, BorderLayout.CENTER);
        panelMain.add(panelBottom, BorderLayout.SOUTH);
        
        this.add(panelMain);
        this.setVisible(true);
        while(true){
            String a = new String();
            a = din.readUTF();
            txtChatLog.append(a);
        }
    }
    
    public static void main(String[] args) throws HeadlessException, IOException {
        Client client = new Client();
    }
}
