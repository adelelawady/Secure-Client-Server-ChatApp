package com.socket;



import EmployeeChat.MSG;
import com.sun.java.accessibility.util.EventID;
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;

class ServerThread extends Thread {
    
    public SocketServer server = null;
    public Socket socket = null;
    public int ID = -1;
    public String username = "";
    public ObjectInputStream streamIn  =  null;
    public ObjectOutputStream streamOut = null;
    public ServerFrame ui;
    public Color WhiteColor = new Color(236, 240, 241);
    public ServerThread(SocketServer _server, Socket _socket){
        super();
        server = _server;
        socket = _socket;
        
        ID = socket.getPort();
        ui = _server.ui;
    }
    
    public void send(Message msg){
        try {
            
            server.AnnounceIN_OUT(msg,true);
            streamOut.writeObject(msg);
            streamOut.flush();
            
        }
        catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }
    
    public int getID(){
        return ID;
    }
    
    @SuppressWarnings("deprecation")
    public void run(){
       
        server.AnnounceServer("[Client] Thread " + ID + " Connected.\n");
        
        while (true){
            try{
                
                Message msg = (Message) streamIn.readObject();
                // server.AnnounceIN_OUT("Outgoing : " + msg.toString(),true);
                //   if(!msg.equals(null)){
                server.AnnounceIN_OUT(msg,false);
                
                server.handle(ID, msg);
                
                // }
                
            }catch(Exception ioe){
                
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(ID);
                stop();
                
                
            }
        }
    }
    
    public void open() throws IOException {
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
        streamIn = new ObjectInputStream(socket.getInputStream());
    }
    
    public void close() throws IOException {
        socket.close();
        streamIn.close();
        streamOut.close();
    }
}





public class SocketServer implements Runnable {
    public Color WhiteColor = new Color(236, 240, 241);
    public Color UserColor = new Color(230, 126, 34);
    public ServerThread clients[];
    public ServerSocket server = null;
    public Thread       thread = null;
    public int clientCount = 0, port = 13000;
    public ServerFrame ui;
    // public Database db;
     String RANDKEY=Security.KEY.randomAlpha(16);
    public SocketServer(ServerFrame frame){
           ui = frame;
           
           System.out.println("KEY : " +RANDKEY);
           
         
         
        clients = new ServerThread[50];
     
       
        try{
            server = new ServerSocket(port);
           
            AnnounceServer("[Server] startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort()+"\n");
            start();
        }
        catch(IOException ioe){
           
            AnnounceServer("[Error]  Can not bind to port : " + port );
//          /  ui.RetryStart(0);
        }
        
        
        AnnounceServer("[SERVER_SECURITY] KEY GENRATED : " + RANDKEY);
    }
    
    public SocketServer(ServerFrame frame, int Port){
        
        clients = new ServerThread[50];
        ui = frame;
        port = Port;
        
        try{
            server = new ServerSocket(port);
            port = server.getLocalPort();
           
            AnnounceServer("[Server] startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort()+ "\n");
            start();
        }
        catch(IOException ioe){
           
            AnnounceServer("[Error] Can not bind to port " + port + ": " + ioe.getMessage());
        }
    }
    
    public void run(){
        while (thread != null){
            try{
                
                addThread(server.accept());
            }
            catch(Exception ioe){
                AnnounceServer("[\nError] ");
                AnnounceServer("Server accept error: \n");
              //  ui.RetryStart(0);
            }
        }
    }
    
    public void start(){
        if (thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }
    
    @SuppressWarnings("deprecation")
    public void stop(){
        if (thread != null){
            thread.stop();
            thread = null;
        }
    }
    
    private int findClient(int ID){
        for (int i = 0; i < clientCount; i++){
            if (clients[i].getID() == ID){
                return i;
            }
        }
        return -1;
    }
    
    
    public synchronized void handle(int ID, Message msg){
       
            
            if(msg.type.equals("message")){
                
                //message handler 
                try {
                   
                    if(msg.recipient.equals("All")){
                      
                       
                        MSG NEWMSG=(MSG)msg.content;
                        NEWMSG.ReciveDate=Calendar.getInstance().getTime();
                      
                        Announce("message", msg.sender, msg.content);
                        
                        
                    }else{
                        
                        findUserThread(msg.recipient).send(new Message(msg.type, msg.sender, msg.content, msg.recipient));
                        clients[findClient(ID)].send(new Message(msg.type, msg.sender, msg.content, msg.recipient));
                        
                    }
                } catch (Exception e) {
                    System.out.println("271 " +e.getMessage());
                }
            }else if (msg.type.equals("KEYREQUIEST")) {
                
                //send random key 
               
            clients[findClient(ID)].send (new Message("KEYREQUIEST", msg.sender ,RANDKEY,""+msg.sender));
         
          
            }else if (msg.type.equals("Connect")) {
               // send connect ok
                clients[findClient(ID)].send(new Message("Connect", "SERVER", "OK", ""+msg.sender));
                  clients[findClient(ID)].username=msg.sender;
                  
                //welcome message to new user only
                  
                MSG Wmsg =new MSG(Calendar.getInstance().getTime(),Calendar.getInstance().getTime() ,"WELCOME TO SERVER "+msg.sender ,null,"All","SERVER");
                Wmsg.EncryptionTYPE="PlanText";
                clients[findClient(ID)].send(new Message("message", "SERVER", Wmsg, ""+msg.sender));
                
                
                   //announce all new user in to all users
                MSG newusermsg =new MSG(Calendar.getInstance().getTime(),Calendar.getInstance().getTime() ,msg.sender +" Joined SERVER ",null,"All","SERVER");
               newusermsg.EncryptionTYPE="PlanText";
                
                 Announce("message", "SERVER",newusermsg);
                
            }
        
    }
    
    
    
    public void Announce(String type, String sender, Object content) {
       
        for (int i = 0; i < clientCount; i++) {
             Message msg = new Message(type, sender, content,clients[i].username);
            clients[i].send(msg);
        }
    }
    public void Announce(String type, String sender, Object content,String _sender) {
        Message msg = new Message(type, sender, content, _sender);
        for (int i = 0; i < clientCount; i++)
            if (!clients[i].username.equals(_sender)){
                clients[i].send(msg);
                System.out.println(clients[i].username);
            }
    }
    public void AnnounceServer(String Content) {
        ui.AppandPanel(Content);
        
    }
    public void AnnounceMESSAGES(String Content) {
        
        ui.AppandPanel2(Content);
        
    }
    public void AnnounceIN_OUT(Message msg,boolean ISOUT) {
        String Res;
        if(ISOUT){
            //out going
           Res=("\n[Outgoing] " + "'"+msg.sender+"' [" );
           
            
        }else{
            Res=("\n[InComing] : " + "'"+msg.sender+"' [" );
           
        }
        
         Res+=("Content Type : ");
        
        
            
        Res+=("('"+msg.content.getClass().getSimpleName()+"'-"+msg.type+")|");
        
        Res+=("Recipient: ");
        Res+=("'"+msg.recipient+"'");
        AnnounceMESSAGES(Res+"]\n" );
    }
    public void SendUserList(String toWhom){
         for(int i = 0; i < clientCount; i++){
            
            findUserThread(toWhom).send(new Message("newuser", "SERVER", clients[i].username, toWhom));
        }
       
    }
    
    public ServerThread findUserThread(String usr){
        for(int i = 0; i < clientCount; i++){
            if(clients[i].username.equals(usr)){
                return clients[i];
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public void remove(int ID){
        int pos = findClient(ID);
        if (pos >= 0){
            
            ServerThread toTerminate = clients[pos];
            
           
            ui.AppandPanel("[Server] Removing client thread " + ID + " at " + pos+"\n");
            
            if (!toTerminate.username.isEmpty()){
                
            
            
                AnnounceServer("[LogOut] Logged Out\n");
                
            }
            
            if (pos < clientCount-1){
                for (int i = pos+1; i < clientCount; i++){
                    clients[i-1] = clients[i];
                }
            }
            clientCount--;
            try{
                toTerminate.close();
                
                Announce("Removeuser", "SERVER", toTerminate.username);
              
            }
            catch(IOException ioe){
                
                AnnounceServer("[Error] closing thread: " + ioe);
            }
            toTerminate.stop();
        }
    }
    
    private void addThread(Socket socket){
        if (clientCount < clients.length){
            AnnounceServer("\n[Client accepted]  " + socket.toString());
            clients[clientCount] = new ServerThread(this, socket);
            try{
                clients[clientCount].open();
                clients[clientCount].start();
                clientCount++;
            }catch(IOException ioe){
              
            }
        }
        else{
            
            AnnounceServer("[Error] Client refused: maximum " + clients.length + " reached.\n");
        }
    }
}
