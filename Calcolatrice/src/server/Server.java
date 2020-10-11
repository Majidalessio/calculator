
package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
    ServerSocket socket_server=null;
    Socket socket_client=null;
    String clientMessage1=null;     //first number
    String clientMessage2=null;     //operand
    String clientMessage3=null;     //second number
    String risposta_server=null;    //data from 
    BufferedReader clientData;      //data from the client to the server
    DataOutputStream serverData;    //data from the server to the client
    String plus="+";
    String minus="-";
    String times="x";
    String dividedBy="/";
    
    public Socket attendi()
    {
        try 
        {
            System.out.println("Server running...");
            socket_server=new ServerSocket(2560);
            System.out.println("Waiting for the client...");
            socket_client=socket_server.accept();
            System.out.println("Client connected.");
            clientData=new BufferedReader(new InputStreamReader(socket_client.getInputStream()));
            serverData=new DataOutputStream(socket_client.getOutputStream());
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore nell'istanziamento del server.");
            System.exit(1);
        }
        return(socket_client);
    }
    public void comunica()
    {
        try 
        {
            System.out.println("Waiting for the first number.");
            clientMessage1=clientData.readLine();
            System.out.println(clientMessage1);
            System.out.println("Waiting for the operand.");
            clientMessage2=clientData.readLine();
            System.out.println("Waiting for the second number.");
            clientMessage3=clientData.readLine();
            System.out.println("Calculating...");
           if(clientMessage2.equals(plus))
            {
                float appo=Float.parseFloat(clientMessage1)+Float.parseFloat(clientMessage3);
                risposta_server=Float.toString(appo);
            }
           if(clientMessage2.equals(minus))
            {
                float appo=Float.parseFloat(clientMessage1)-Float.parseFloat(clientMessage3);
                risposta_server=Float.toString(appo);
            }
           if(clientMessage2.equals(times))
            {
                float appo=Float.parseFloat(clientMessage1)*Float.parseFloat(clientMessage3);
                risposta_server=Float.toString(appo);
            }
            if(clientMessage2.equals(dividedBy))
            {
                float appo=Float.parseFloat(clientMessage1)/Float.parseFloat(clientMessage3);
                risposta_server=Float.toString(appo);
            }
            System.out.println("Sending the result.");
            serverData.writeBytes(risposta_server+'\n');
            System.out.println("Closing the communication.");
            socket_client.close();
        }
        catch (Exception e) 
        {
            System.out.println("Error during the communication");
        }
    }
}
