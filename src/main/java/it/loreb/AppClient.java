package it.loreb;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppClient {
    
    String serverIp;
    int serverPort;
    BufferedReader input;
    Socket mySocket;
    String myStr;
    String serverStr;
    DataOutputStream serverOutput;
    BufferedReader serverInput;

    AppClient(String server_ip, int server_port)
    {
        System.out.println("Creazione CLIENT. Server Ip: " + server_ip + " Server Port: " + server_port + "\n");        
        serverPort = server_port;
        serverIp = server_ip;
        try {
            input = new BufferedReader(new InputStreamReader(System.in));
            mySocket = new Socket(serverIp, serverPort);
            serverOutput = new DataOutputStream(mySocket.getOutputStream());
            serverInput = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host! " + "\n");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Uh oh! Something went wrong..." + "\n");
            System.out.println(e.getMessage());
        }
    }

    void comunicate()
    {
        try
        {
            System.out.println("Time to comunicate! Insert string." + "\n");
            myStr = input.readLine();

            System.out.println("Sending string to server..." + "\n");
            serverOutput.writeBytes(myStr + "\n");

            System.out.println("Reply from server..." + "\n");
            System.out.println(serverInput.readLine());

            System.out.println("Fine connessione. " + "\n");
            mySocket.close();
        }
        catch(IOException e)
        {
            System.out.println("Something is wrong with your string. " + "\n");
            if (myStr != null)
            {
                System.out.println("The string in question: " + myStr + "\n");
            }
            else
            {
                System.out.println("The string couldn't be displayed. (Value is equal NULL)" + "\n");
            }
        }
    }

}
