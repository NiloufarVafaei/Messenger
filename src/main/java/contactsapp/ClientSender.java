/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsapp;


import Entity.Request;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.codehaus.groovy.tools.shell.IO;

/**
 *
 * @author niloufar
 */
public class ClientSender extends Thread {
      public void sendUserName(String communicateUserName, String SignInrequestUserName) {
      try {
            Request req=new Request();
            req.setRequestUserName(SignInrequestUserName);
            req.setAction(1);
            req.setCommunicateUserName(communicateUserName);
            
            Socket s = new Socket("localhost", 5000);
            //Socket socket = socket("http://localhost", 5000);
            ObjectOutputStream dos = new ObjectOutputStream((s.getOutputStream()));
            dos.writeObject(req);
         
            
           // dos.writeUTF(contact);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
      }
      

    public void sendMessage(String SinginrequestUserName,String communicateUserName,String message) throws IOException {
           try {
         Request req=new Request();
            req.setAction(2);
            req.setCommunicateUserName(communicateUserName);
            req.setMessage(message);
            Socket s = new Socket("localhost", 5000);
            //Socket socket = socket("http://localhost", 5000);
            ObjectOutputStream dos = new ObjectOutputStream((s.getOutputStream()));
            dos.writeObject(req);
           }catch (IOException ie) {
            ie.printStackTrace();}
    }
}
