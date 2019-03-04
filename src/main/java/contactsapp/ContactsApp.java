/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsapp;

import Entity.Contact;
import Entity.User;
import Entity.Request;
import Entity.ResponseServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author niloufar
 */
public class ContactsApp {

    static User SinginrequestUserName = new User();
    static boolean isInPrivateChate = false;

    public static void DrawMainPage() throws IOException, InterruptedException {


        System.out.println("--------PHONE BOOK--------------------------");
        System.out.println("|                                          |");
        System.out.println("|       1:ADD                              |");
        System.out.println("|       2:DELETE                           |");
        System.out.println("|       3:SEARCH                           |");
        System.out.println("|       4:IMPORT JSON FILE                 |");
        System.out.println("|       5:MESSENGER                        |");
        System.out.println("|       0:EXIT                             |");
        System.out.println("--------------------------------------------");
        System.out.printf("   PLEASE ENTER NUMBER OF ACTION:");

        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        while (!(action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4") || action.equals("5")
                || action.equals("0"))) {
            System.out.printf("Wrong input Number! Please Enter again:");
            action = scanner.nextLine();
        }

        switch (Integer.parseInt(action)) {
            case 1:
                DrawAddPage();
            case 2:
                DrawDeletePage();
            case 3:
                DrawSearchPage();
            case 4:
                DrawImportJSONPage();
            case 5:
                DrawMessengerPage();
            case 0:
                System.exit(0);
        }

    }

    public static void main(String[] args) throws IOException, JSONException, InterruptedException {

        DrawMainPage();
    }

    private static void DrawAddPage() throws IOException, InterruptedException {
        System.out.println("--------PHONE BOOK/Add--------------------------");
        System.out.println("For Cancel add action please press ESC");
        Contact contact = new Contact();
        System.out.println("Name:");
        Scanner scanner = new Scanner(System.in);
        contact.setName(scanner.nextLine());
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        if (contact.getName().equals("ESC")) {
            Runtime.getRuntime().exec("cls");
            //we still dosen't have this functienality
            DrawMainPage();
        } else {
            while (contact.getName().equals("")) {
                System.out.println("Name is required!");
                System.out.println("Name:");
                contact.setName(scanner.nextLine());
            }
            System.out.println("Mobile:");
            String mobile = scanner.nextLine();
            String pattern = "^[0-9]*$";
            while (mobile.equals("") || !mobile.matches(pattern)) {
                System.out.println("Mobile is required!");
                System.out.println("Mobile:");
                mobile = scanner.nextLine();
            }
            contact.setMobile(Long.parseLong(mobile));

            System.out.println("Home Number:");
            String HomeNumber = scanner.nextLine();
            if (HomeNumber.equals("")) {
                contact.setHomeNumber(Long.parseLong("0"));
            }
            System.out.println("IP:");
            String IP = scanner.nextLine();
            while (IP.equals("")) {
                System.out.println("IP is required!");
                System.out.println("IP:");
                IP = scanner.nextLine();
            }
            contact.setIP(IP);

            Opreation opr = new Opreation();
            int result = opr.AddContact(contact);
            if (result == 1) {
                System.out.println("Insert was successfull");
            } else if (result == 0) {
                System.out.println("duplicate record");
            } else {
                System.out.println("ERROR");
            }

            System.out.println("Please enter a key...");
            scanner.nextLine();
            // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            DrawMainPage();
        }
    }

    private static void DrawDeletePage() throws IOException, InterruptedException {
        System.out.println("--------PHONE BOOK/Delete--------------------------");
        System.out.println("for deleting a contact please enter a name:");
        System.out.println("Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Opreation opr = new Opreation();
        int result = opr.Delete(name);
        if (result == 1) {
            System.out.println("DELETE was successfull.");
        } else if (result == 0) {
            System.out.println("Record not found");
        } else {
            System.out.println("ERROR");
        }
        System.out.println("Please enter a key...");
        scanner.nextLine();
        // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        DrawMainPage();
    }

    private static void DrawSearchPage() throws IOException, InterruptedException {
        System.out.println("--------PHONE BOOK/Search--------------------------");
        System.out.println("Please enter a name or a mobile:");
        System.out.println("Input:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Opreation opr = new Opreation();
        ArrayList<Contact> result = opr.Search(input);
        if (result.isEmpty()) {
            System.out.println("Record not found");
        } else {
            System.out.println("-----------------------------------------------");
            System.out.println("|   NAME    |   PHONE    |   HOME    |   IP   |");
            System.out.println("-----------------------------------------------");
            for (Contact c : result) {
                System.out.println("|  " + c.getName() + "  |  " + c.getMobile() + "  |  " + c.getHomeNumber() + "  |" + c.getIP() + "  |");
                System.out.println("-------------------------------------");
            }
        }
        System.out.println("Please enter a key...");
        scanner.nextLine();
        // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        DrawMainPage();

    }

    private static void DrawImportJSONPage() throws IOException, InterruptedException {
        System.out.println("--------PHONE BOOK/Import JSON File--------------------------");
        System.out.println("Please enter a file path");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        Opreation opr = new Opreation();
        int result = opr.ImportJson(path);
        if (result == 1) {
            System.out.println("File was imported successfull.");
        } else {
            System.out.println("ERROR");
        }
        System.out.println("Please enter a key...");
        scanner.nextLine();
        // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        DrawMainPage();

    }

    private static void DrawMessengerPage() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter UserName...");
        String Un = scanner.nextLine();
        while (Un == "") {
            System.out.println("Please enter UserName...");
            Un = scanner.nextLine();
        }
        SinginrequestUserName.setRequestUserName(Un);

        System.out.println("--------MESSENGER--------------------------");
        System.out.println("|                                          |");
        System.out.println("|       1:SHOW CONTACT                     |");
        System.out.println("|       2:PRIVATE CHAT                     |");
        System.out.println("|       3:BACK TO MAIN MENU                |");
        System.out.println("--------------------------------------------");
        System.out.printf("   PLEASE ENTER NUMBER OF ACTION:");
        String action = scanner.nextLine();
        while (!(action.equals("1") || action.equals("2") || action.equals("3"))) {
            System.out.printf("Wrong input Number! Please Enter again:");
            action = scanner.nextLine();
        }

        if (action.equals("1")) {
            DrawListContact();

        } else if (action.equals("2")) {
            DrawFindUserMenu();

        } else if (action.equals("3")) {
            DrawMainPage();
        }

    }

    private static void DrawListContact() throws InterruptedException, IOException {
        Opreation opr = new Opreation();
        ArrayList<Contact> result = opr.Search("");
        if (result.isEmpty()) {
            System.out.println("Contact is empty");
        } else {
            System.out.println("--------------------------- -");
            System.out.println("|   NAME       |   IP       |");
            System.out.println("-----------------------------");
            for (Contact c : result) {
                System.out.println("|  " + c.getName() + "  |  " + c.getIP() + "  |");
                System.out.println("-------------------------------------");
            }
        }
        System.out.println("Please enter a key...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        DrawMessengerPage();
    }

    private static void DrawFindUserMenu() throws InterruptedException, IOException {
        //start thread listen-------------

        ClientSender srq = new ClientSender();

        ClientListener listen = new ClientListener();
        Thread ListenThread = new Thread(listen);
        ListenThread.start();

        System.out.println("Who do you want to send messege?");
        Scanner scanner = new Scanner(System.in);
        String communicateUserName = scanner.nextLine();
        srq.sendUserName(communicateUserName, SinginrequestUserName.requestUserName);

//        String IP=null;
//        if (IP != null) {
//            System.out.println("--------Private Chat--------------------------");
//            System.out.println("----For Exit Enter 0-------------------");
//            System.out.printf("me:");
//            String message = scanner.nextLine();
//            ClientSender sender = new ClientSender();
//
//            while (!message.equals("0")) {
//                sender.send(message, IP);
//                TimeUnit.SECONDS.sleep(1);
//                System.out.printf("me:");
//                message = scanner.nextLine();
//            }
//            DrawMessengerPage();
//        } else {
//            System.out.println("Contact NOT Found...");
//            ListenThread.stop();
//            scanner.nextLine();
//            DrawMessengerPage();
//        }
        ClientListener r=new ClientListener();
        r.run(); 
        
    }

    public static void DrawPrivateChat(ResponseServer rs) throws InterruptedException, IOException {
        isInPrivateChate = true;
        //ResponseServer communicateUserName = new ResponseServer();
        System.out.println("--------Private Chat--------------------------");
        System.out.println("----For Exit Enter 0-------------------");
        while (true) {
            String communicateUserName = rs.getCommunicateUserName();
            System.out.printf("me:");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            ClientSender sendMessage = new ClientSender();
            sendMessage.sendMessage(SinginrequestUserName.getRequestUserName(), communicateUserName, message);
        }
    }

    public static void ShowMessage(ResponseServer rs) throws InterruptedException, IOException {
        if (isInPrivateChate = !true) {

            System.out.println("--------Private Chat--------------------------");
            System.out.println("----For Exit Enter 0-------------------");
            System.out.printf("---");
            TimeUnit.SECONDS.sleep(1);
            System.out.printf(rs.getMessage());
            //read new message from requestUserName
            while (true) {
                String communicateUserName = rs.getCommunicateUserName();
                System.out.printf("me:");
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                ClientSender sendMessage = new ClientSender();
                sendMessage.sendMessage(SinginrequestUserName.getRequestUserName(), communicateUserName, message);
            }
        } else {
            System.out.printf("---");
            TimeUnit.SECONDS.sleep(1);
            System.out.printf(rs.getMessage());
            //read new message from requestUserName
            while (true) {
                String communicateUserName = rs.getCommunicateUserName();
                System.out.printf("me:");
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                ClientSender sendMessage = new ClientSender();
                sendMessage.sendMessage(SinginrequestUserName.getRequestUserName(), communicateUserName, message);
            }
        }
    }
}
