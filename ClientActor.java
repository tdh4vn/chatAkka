/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientActor;

import akka.actor.Actor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import sun.net.www.protocol.http.AuthCacheValue;

/**
 *
 * @author Tdh4vn
 */
public class ClientActor extends UntypedActor{
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public Socket ClientSocket;
    public DataInputStream din;
    public DataOutputStream dout;
    
    public ClientActor(Socket soc) throws IOException{
        ClientSocket = soc;
        System.out.println(soc.toString());
        din = new DataInputStream(ClientSocket.getInputStream());
        dout = new DataOutputStream(ClientSocket.getOutputStream());
    }
    
    public void run(){
        while(true){
            
        }
    }
    
    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof String){
            dout.writeUTF((String) o);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
