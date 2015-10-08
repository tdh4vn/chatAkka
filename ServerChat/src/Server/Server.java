/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import ClientActor.ClientActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Tdh4vn
 */
public class Server extends Thread{
    JFrame frmMain;
    private JTextArea txtArea;
    public static Vector ClientSockets;
    public static Vector LoginNames;
    ServerSocket soc;
    
    public Server() throws IOException{
        soc = new ServerSocket(9090);
        ClientSockets = new Vector();
        LoginNames = new Vector();
        frmMain = new JFrame();
        frmMain.setTitle("Server");
        frmMain.setSize(400,400);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setLayout(new BorderLayout());
        txtArea = new JTextArea(50,50);
        frmMain.add(txtArea, BorderLayout.CENTER);
        frmMain.setVisible(true);
        start();
    }
    
    public void run(){
        try{
            while(true)
            {
                Socket CSoc = soc.accept();
                txtArea.append("Connected");
                ActorSystem actorSystem = ActorSystem.create("SystemActorClient");
                ActorRef actorRefClient = actorSystem.actorOf(Props.create(ClientActor.class, CSoc), "Actor");
                actorRefClient.tell("tin nhan", actorRefClient);
                ClientSockets.add(actorRefClient);
            }
            
                
              
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    } 
    
    public static void main(String[] args) throws Exception {
        Server sv = new Server();
    }
}
