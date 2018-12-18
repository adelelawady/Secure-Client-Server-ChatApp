/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.socket;

/**
 *
 * @author adelElawady
 */

import EmployeeChat.MSG;
import java.io.*;
import java.net.*;

public class SocketClient implements Runnable {
    
    public int port;
    public String serverAddr;
    public Socket socket;
    
    public ObjectInputStream In;
    public ObjectOutputStream Out;
     EmployeeChat.ChatFrame fram;
 
    public SocketClient(String _ip,int _port ,EmployeeChat.ChatFrame chtfram) throws IOException {
       fram=chtfram;
        this.serverAddr = _ip;
        this.port = _port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
        //Check Server
      
       
    }
    
    @Override
    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            
            try {
                
                Message msg=(Message) In.readObject();
                fram.HandleMsg(msg);
            } catch (Exception ex) {
                ex.printStackTrace();
                keepRunning = false;
                
            }
        }
    }
   
    
    
    public void send(Message msg) {
        try {
            Out.writeObject(msg);
            Out.flush();
            
           
            
        } catch (IOException ex) {
            
            System.out.println("Exception SocketClient send()");
        }
    }
    public void closeThread(Thread t) {
        t = null;
    }
}
