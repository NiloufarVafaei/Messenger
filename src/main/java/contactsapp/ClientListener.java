package contactsapp;

import Entity.ResponseServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author niloufar
 */
public class ClientListener implements Runnable {

    @Override
    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(5000);
        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {

            Socket s = null;
            try {
                s = ss.accept();

                ObjectInputStream object = null;

                object = new ObjectInputStream(s.getInputStream());

                ResponseServer rs = null;

                rs = (ResponseServer) object.readObject();
                switch (rs.getResAction()) {
                    case 1:
                        //recive message
                        if (rs.isHasData()) {
                            //creat private chat
                            ContactsApp c = null;
                            c.DrawPrivateChat(rs);
                            
                            
                        }

                    case 2:
                    if(ContactsApp.isInPrivateChate){
                    ContactsApp.ShowMessage(rs);
                    }
                    case 0:
                        System.exit(0);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
